package com.hjy.sjzyml.dao;

import com.hjy.sjzyml.entity.TCatalog;
import com.hjy.sjzyml.entity.TData;

import java.util.List;

/**
 * (TData)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-02 15:01:03
 */
public interface TDataMapper {

    int insertSelective(TData tData);

    int update(TData tData);

    int deleteById(String pk_id);

    TData selectByPkId(String pk_id);

    List<TData> selectAllPage(TData entity);
}