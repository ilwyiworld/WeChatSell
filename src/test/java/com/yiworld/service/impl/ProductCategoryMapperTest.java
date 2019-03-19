package com.yiworld.service.impl;

import com.yiworld.dataobject.ProductCategory;
import com.yiworld.dataobject.mapper.ProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("category_name","yiworld最爱");
        map.put("category_type",101);
        int result= mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("yiworld最不爱");
        productCategory.setCategoryType(102);
        int result= mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType() throws Exception {
        ProductCategory productCategory= mapper.findByCategoryType(102);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName() throws Exception {
        List<ProductCategory> productCategoryList= mapper.findByCategoryName("yiworld最不爱");
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void updateByCategoryType() throws Exception {
        int result= mapper.updateByCategoryType("yiworld最不爱嘛嘛嘛修改后",123);
        Assert.assertEquals(1,result);
    }
    @Test
    public void updateByObject() throws Exception {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("yiworld最不爱改回来");
        productCategory.setCategoryType(102);
        int result= mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType() throws Exception {
        int result= mapper.deleteByCategoryType(123);
        Assert.assertEquals(1,result);
    }

}