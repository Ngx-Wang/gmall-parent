package com.atguigu.service.impl;

import com.atguigu.activity.SeckillGoods;
import com.atguigu.constant.RedisConst;
import com.atguigu.mapper.SecKillMapper;
import com.atguigu.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    SecKillMapper secKillMapper;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void putGoods() {
        List<SeckillGoods> seckillGoods = secKillMapper.selectList(null);
        for (SeckillGoods seckillGood : seckillGoods) {
            Integer num = seckillGood.getNum();
            for (int i = 0; i <num ; i++) {
                redisTemplate.opsForList().leftPush(RedisConst.SECKILL_STOCK_PREFIX+seckillGood.getSkuId(),seckillGood.getSkuId());
            }
            redisTemplate.opsForHash().put(RedisConst.SECKILL_GOODS,seckillGood.getSkuId()+"",seckillGood);
            redisTemplate.convertAndSend("seckillpush",seckillGood.getSkuId()+":1");
        }
    }

    @Override
    public void publishGoods(Long skuId) {
        redisTemplate.convertAndSend("seckillpush",skuId+":1");
    }


    @Override
    public void unpublishGoods(Long skuId) {
        redisTemplate.convertAndSend("seckillpush",skuId+":0");

    }

    @Override
    public List<SeckillGoods> getSecKillGoodsList() {
        List<SeckillGoods> list = redisTemplate.opsForHash().values(RedisConst.SECKILL_GOODS);
        return list;
    }

    @Override
    public SeckillGoods getSeckillGoods(Long skuId) {
        SeckillGoods seckillGood = (SeckillGoods)redisTemplate.boundHashOps(RedisConst.SECKILL_GOODS).get(skuId+"");
        return seckillGood;
    }
}
