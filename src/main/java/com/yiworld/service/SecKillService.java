package com.yiworld.service;

/**
 * Created by Administrator on 2018/3/16.
 */
public interface SecKillService {
    //查询秒杀商品库存
    String querySecKillProductInfo(String productId);
    //秒杀商品
    void orderProductMockDiffUser(String productId);
}
