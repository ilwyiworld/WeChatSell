package com.yiworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.yiworld.dataobject.mapper")
@EnableCaching
public class WeChatSellApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeChatSellApplication.class, args);
	}
}
