package com.hjy.sjzyml.dao;

import com.hjy.sjzyml.entity.TCatalog;

import java.util.List;

/**
 * (TCatalog)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-02 15:01:01
 */
public interface TCatalogMapper {

    int insertSelective(TCatalog tCatalog);

    int deleteById(String pk_id);

    List<TCatalog> selectAllPage(TCatalog catalog);

    TCatalog selectByPkId(String pk_id);

    int update(TCatalog tCatalog);
}