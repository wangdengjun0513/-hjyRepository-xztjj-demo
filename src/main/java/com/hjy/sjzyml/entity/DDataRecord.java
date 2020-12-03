package com.hjy.sjzyml.entity;

import lombok.Data;

import java.util.Date;

/**
 * (ReDataRecord)表实体类
 *
 * @author makejava
 * @since 2020-12-02 15:00:58
 */
@Data
public class DDataRecord {

    private String pkDatarecordId;
    //数据项ID
    private String fkDataId;
    //更新时间
    private Date updateTime;
    //更新人
    private String updatePeople;

}