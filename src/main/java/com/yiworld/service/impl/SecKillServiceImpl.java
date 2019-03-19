package com.yiworld.service.impl;

import com.yiworld.exception.SellException;
import com.yiworld.service.RedisLock;
import com.yiworld.service.SecKillService;
import com.yiworld.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecKillServiceImpl implements SecKillService {

    private static final int TIMEOUT=10*1000;   //超时时间10秒
    @Autowired
    private RedisLock redisLock;

    static Map<String,Integer> products;
    static Map<String,Integer> stocks;
    static Map<String,String> orders;

    static{
        products= new HashMap<String,Integer>();
        stocks= new HashMap<String,Integer>();
        orders= new HashMap<String,String>();
        products.put("123456",100000);
        stocks.put("123456",100000);
    }

    private String queryMap(String productId){
        return "活动，皮蛋粥特价，限量份"+products.get(productId)+" 还剩："+
                stocks.get(productId)+"份"+
                " 该商品成功下单数量"+orders.size()+" 人";
    }

    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time=System.currentTimeMillis()+TIMEOUT;
        if(!redisLock.lock(productId,String.valueOf(time))){
            //加锁不成功
            throw new SellException(101,"加锁失败，人太多了");
        }
        //1.查询商品库存，若为0，则已结束
        int num=stocks.get(productId);
        if(num ==0){
            throw new SellException(100,"活动已结束");
        }else{
            //2.下单（模拟不用用户 openid不同）
            orders.put(KeyUtil.genUniqueKey(),productId);
            //3.减库存
            num=num-1;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stocks.put(productId,num);
        }
        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }
}
