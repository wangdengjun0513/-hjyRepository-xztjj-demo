package com.hjy.sjzyml.dao;

import com.hjy.sjzyml.entity.ReCatalogData;

/**
 * (ReCatalogData)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-02 15:00:52
 */
public interface ReCatalogDataMapper {

    int insertSelective(ReCatalogData reCatalogData);
}