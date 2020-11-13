package com.atguigu.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.ProductFeignClient;
import com.atguigu.list.Goods;
import com.atguigu.list.SearchParam;
import com.atguigu.list.SearchResponseTmVo;
import com.atguigu.list.SearchResponseVo;
import com.atguigu.repository.GoodsRepository;
import com.atguigu.service.ListService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;

import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        //检索
        searchSourceBuilder.query(boolQueryBuilder);

        //分组，提取平台属性
        searchSourceBuilder.aggregation(AggregationBuilders.terms("tmIdAgg").field("tmId")
                .subAggregation(AggregationBuilders.terms("tmNameAgg").field("tmName"))
                .subAggregation(AggregationBuilders.terms("tmLogoUrlAgg").field("tmLogoUrl")));

        System.out.println(searchSourceBuilder.toString());// 打印dsl语句
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();

            SearchHit[] hitsResult = hits.getHits();
            List<Goods> goodsList = new ArrayList<>();
            for (SearchHit documentFields : hitsResult) {
                String sourceAsString = documentFields.getSourceAsString();
                Goods good = JSON.parseObject(sourceAsString, Goods.class);
                goodsList.add(good);
            }
            searchResponseVo.setGoodsList(goodsList);

            // 解析聚合
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        return searchResponseVo;
    }


}
