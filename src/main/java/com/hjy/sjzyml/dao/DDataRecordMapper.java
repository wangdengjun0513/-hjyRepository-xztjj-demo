package com.hjy.sjzyml.dao;

import com.hjy.sjzyml.entity.DDataRecord;

/**
 * (ReDataRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-02 15:00:58
 */
public interface DDataRecordMapper {

    int insertSelective(DDataRecord dDataRecord);
}