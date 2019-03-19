package com.yiworld.repository;

import com.yiworld.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> /*主键int类型*/{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
