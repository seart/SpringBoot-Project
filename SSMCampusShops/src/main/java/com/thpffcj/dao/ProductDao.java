package com.thpffcj.dao;

import com.thpffcj.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Thpffcj on 2018/1/5.
 */
public interface ProductDao {

    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id,商品类别
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(
            @Param("productCondition") Product productCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);
}
