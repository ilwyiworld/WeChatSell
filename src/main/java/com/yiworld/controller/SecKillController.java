package com.yiworld.controller;

import com.yiworld.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/3/16.
 */
@RestController
@RequestMapping("/secKill")
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService secKillService;

    //查询秒杀活动特价商品的信息
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId)throws Exception{
        return secKillService.querySecKillProductInfo(productId);
    }

    //秒杀，没有抢到返回“哎哟喂，xxxxx”，抢到了会返回剩余的库存量
    @GetMapping("/order/{productId}")
    public String secKill(@PathVariable String productId)throws Exception{
        log.info("@SecKill request,productId:"+productId);
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }
}
