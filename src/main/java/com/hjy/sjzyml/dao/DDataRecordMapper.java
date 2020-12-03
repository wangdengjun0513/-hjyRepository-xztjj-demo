package com.hjy.sjzyml.dao;

import com.hjy.sjzyml.entity.DDataRecord;
import com.hjy.sjzyml.entity.TData;

import java.util.List;

/**
 * (ReDataRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-02 15:00:58
 */
public interface DDataRecordMapper {

    int insertSelective(DDataRecord dDataRecord);

    /**
     * 通过数据项ID查询该数据项修改时间线记录
     * @param pk_id
     * @return
     */
    List<DDataRecord> selectRecordByDataId(String pk_id);
}