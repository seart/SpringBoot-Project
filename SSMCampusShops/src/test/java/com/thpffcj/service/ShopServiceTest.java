package com.thpffcj.service;

import com.thpffcj.BaseTest;
import com.thpffcj.dao.ShopDao;
import com.thpffcj.dto.ImageHolder;
import com.thpffcj.dto.ShopExecution;
import com.thpffcj.entity.Area;
import com.thpffcj.entity.PersonInfo;
import com.thpffcj.entity.Shop;
import com.thpffcj.entity.ShopCategory;
import com.thpffcj.enums.ShopStateEnum;
import org.apache.commons.fileupload.FileItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.Date;
import java.util.logging.Filter;

import static org.junit.Assert.*;

/**
 * Created by Thpffcj on 2017/12/19.
 */
public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void addShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(4L);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("E://github.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), inputStream);
        ShopExecution shopExecution = shopService.addShop(shop, imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }

    @Test
    public void modifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(39L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("E://github1.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder("github1.jpg", inputStream);
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
        System.out.println("新的图片地址" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void getShopList() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shopCondition.setShopCategory(shopCategory);
        ShopExecution shopExecution = shopService.getShopList(shopCondition, 2, 2);
        System.out.println("店铺列表数为：" + shopExecution.getShopList().size());
        System.out.println("店铺总数为：" + shopExecution.getCount());
    }
}