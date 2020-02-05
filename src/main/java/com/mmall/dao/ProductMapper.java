package com.mmall.dao;

import com.google.common.collect.Lists;
import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> selectProductByNameAndId(@Param("productName") String productName,
                                           @Param("id") Integer id);

    List<Product> selectByNameAndCategoryIds(@Param(value = "productName") String productName,
                                             @Param(value = "categoryListIds") List<Integer> categoryListIds);
}