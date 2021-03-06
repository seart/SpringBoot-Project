package com.thpffcj.service;

import com.thpffcj.dto.ImageHolder;
import com.thpffcj.dto.ShopExecution;
import com.thpffcj.entity.Shop;

import java.io.InputStream;

/**
 * Created by Thpffcj on 2017/12/19.
 */
public interface ShopService {

    /**
     * 通过店铺Id获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括图片的处理
     * @param shop
     * @param thumbnail
     * @return
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail);

    ShopExecution addShop(Shop shop, ImageHolder thumbnail);

    /**
     * 根据shopCondition分页返回相应列表数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
