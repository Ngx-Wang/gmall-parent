package com.atguigu.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.ProductFeignClient;
import com.atguigu.list.*;
import com.atguigu.repository.GoodsRepository;
import com.atguigu.service.ListService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    ProductFeignClient productFeignClient;
    @Autowired
    ElasticsearchRestTemplate ElasticsearchRestTemplate;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public void skuOnSale(Long skuId) {
       Goods goods = productFeignClient.GetSkuGoods(skuId);
        goodsRepository.save(goods);
    }

    @Override
    public void cancelSale(Long skuId) {
        goodsRepository.deleteById(skuId);
    }

    //初始化，建立索引，建立数据结构
    @Override
    public void createGoods(){
        ElasticsearchRestTemplate.createIndex(Goods.class);
        ElasticsearchRestTemplate.putMapping(Goods.class);
    }

    @Override
    public void HotSource(Long skuId) {

        Long hotSource = redisTemplate.opsForValue().increment("sku:" + skuId + "hotSource", 1l);
        if (hotSource%10 == 0){
            Optional<Goods> repositoryById = goodsRepository.findById(skuId);
            Goods goods = repositoryById.get();
            goods.setHotScore(hotSource);
            goodsRepository.save(goods);
        }
    }

    @Override
    public SearchResponseVo SearchList(SearchParam searchParam) {
        Long category3Id = searchParam.getCategory3Id();
        String keyword = searchParam.getKeyword();
        String[] props = searchParam.getProps();
        String trademark = searchParam.getTrademark();


        SearchResponseVo searchResponseVo = new SearchResponseVo();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("goods");
        searchRequest.types("info");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(20);
        searchSourceBuilder.from(0);

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //三级分类的id
        if (null!=category3Id && category3Id>0){
            TermQueryBuilder queryBuilder = new TermQueryBuilder("category3Id",category3Id);
            boolQueryBuilder.filter(queryBuilder);
        }
        //关键字
        if (StringUtils.isNotEmpty(keyword)){
            TermQueryBuilder queryBuilder = new TermQueryBuilder("title",keyword);
            boolQueryBuilder.filter(queryBuilder);
            //高亮设置
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.preTags("<span style='color:red;font-weight:bolder'>");
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);

        }

        //属性（数组）
        //pop----->平台属性Id:平台属性值名称:平台属性名，
        if (null != props && props.length>0){

            for (String prop : props) {
                String[] split = prop.split(":");
               Long attrId= Long.parseLong(split[0]);
                String attrValue=  split[1];
                String attrName= split[2];
                //根据id进行结果过滤
                TermQueryBuilder queryBuilder = new TermQueryBuilder("attrId",attrId);
                boolQueryBuilder.filter(queryBuilder);
                //根据平台属性值进行筛选
                MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("attrValue", attrValue);
                boolQueryBuilder.must(matchQueryBuilder);
            }
        }

        //商标   trademark=2:华为  tmId:tmName
        if (StringUtils.isNotEmpty(trademark)){
            String[] split = trademark.split(":");
            Long tmId = Long.parseLong(split[0]);
            TermQueryBuilder queryBuilder = new TermQueryBuilder("tmName",tmId);
            boolQueryBuilder.filter(queryBuilder);
        }

        //排序
        if(StringUtils.isNotBlank(searchParam.getOrder())){
            String order = searchParam.getOrder();
            String[] split = order.split(":");
            String type = split[0];// 1 2
            String sort = split[1];// asc desc

            String name = "hotScore";
            if(type.equals("2")){
                name = "price";
            }
            searchSourceBuilder.sort(name, sort.equals("asc")?SortOrder.ASC: SortOrder.DESC);
        }




        //检索
        searchSourceBuilder.query(boolQueryBuilder);

        //分组，提取商标
        searchSourceBuilder.aggregation(AggregationBuilders.terms("tmIdAgg").field("tmId")
                .subAggregation(AggregationBuilders.terms("tmNameAgg").field("tmName"))
                .subAggregation(AggregationBuilders.terms("tmLogoUrlAgg").field("tmLogoUrl")));

        //分组，提取平台属性及属性值,类型为nested
        //AggregationBuilders.terms("attrsAgg").field("attrs")
        searchSourceBuilder.aggregation(AggregationBuilders.nested("attrsAgg","attrs")
            .subAggregation(AggregationBuilders.terms("attrIdAgg").field("attrs.attrId")
                .subAggregation(AggregationBuilders.terms("attrNameAgg").field("attrs.attrName"))
                .subAggregation(AggregationBuilders.terms("attrValueAgg").field("attrs.attrValue"))));

        System.out.println(searchSourceBuilder.toString());// 打印dsl语句
        searchRequest.source(searchSourceBuilder);//搜索查询
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            //获得命中结果
            SearchHit[] hitsResult = hits.getHits();
            List<Goods> goodsList = new ArrayList<>();
            for (SearchHit documentFields : hitsResult) {
                String sourceAsString = documentFields.getSourceAsString();
                Goods good = JSON.parseObject(sourceAsString, Goods.class);

                Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
                if(null!=highlightFields&&highlightFields.size()>0){
                    HighlightField highlightField = highlightFields.get("title");
                    Text[] texts = highlightField.getFragments();
                    good.setTitle(texts[0].toString());
                }
                goodsList.add(good);

            }
            searchResponseVo.setGoodsList(goodsList);

            // 解析聚合，获得品牌
            ParsedLongTerms TmIdParsedLongTerms = (ParsedLongTerms) searchResponse.getAggregations().get("tmIdAgg");

            List<SearchResponseTmVo> searchResponseTmVos = TmIdParsedLongTerms.getBuckets().stream().map(tmIdBucket -> {
                SearchResponseTmVo searchResponseTmVo = new SearchResponseTmVo();

                // id
                Long tmIdKey = (Long) tmIdBucket.getKey();
                searchResponseTmVo.setTmId(tmIdKey);

                // name
                ParsedStringTerms TmNameParsedStringTerms = (ParsedStringTerms) tmIdBucket.getAggregations().get("tmNameAgg");
                String tmNames = TmNameParsedStringTerms.getBuckets().get(0).getKeyAsString();
                searchResponseTmVo.setTmName(tmNames);
                // logo
                ParsedStringTerms TmLogoUrlParsedStringTerms = (ParsedStringTerms) tmIdBucket.getAggregations().get("tmLogoUrlAgg");
                String tmLogoUrl = TmLogoUrlParsedStringTerms.getBuckets().get(0).getKeyAsString();
                searchResponseTmVo.setTmLogoUrl(tmLogoUrl);

                return searchResponseTmVo;
                }).collect(Collectors.toList());
            searchResponseVo.setTrademarkList(searchResponseTmVos);

            ParsedNested attrsAggParsedNested = searchResponse.getAggregations().get("attrsAgg");
            ParsedLongTerms attrIdAggParsedLongTerms = attrsAggParsedNested.getAggregations().get("attrIdAgg");
            List<SearchResponseAttrVo> searchResponseAttrVos = attrIdAggParsedLongTerms.getBuckets().stream().map(attrIdBucket->{
                SearchResponseAttrVo searchResponseAttrVo = new SearchResponseAttrVo();

                //attrId
                Long key = (Long) attrIdBucket.getKey();

                //当前属性值的集合
                ParsedStringTerms attrValueAggParsedStringTerms = attrIdBucket.getAggregations().get("attrValueAgg");
                List<String> attrValueList = attrValueAggParsedStringTerms.getBuckets().stream().map(attrValueBucket->{
                    return attrValueBucket.getKey().toString();
                }).collect(Collectors.toList());

                //属性名称
                ParsedStringTerms attrNameAggParsedStringTerms = attrIdBucket.getAggregations().get("attrNameAgg");
                String attrName = attrNameAggParsedStringTerms.getBuckets().get(0).getKeyAsString();

                searchResponseAttrVo.setAttrId(key);
                searchResponseAttrVo.setAttrValueList(attrValueList);
                searchResponseAttrVo.setAttrName(attrName);

                return searchResponseAttrVo;
            }).collect(Collectors.toList());
            searchResponseVo.setAttrsList(searchResponseAttrVos);





        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return searchResponseVo;
    }


}
