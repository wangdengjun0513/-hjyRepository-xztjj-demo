package com.hjy.sjzyml.entity;

import lombok.Data;

/**
 * (TCatalog)表实体类
 *
 * @author makejava
 * @since 2020-12-02 15:01:01
 */
@Data
public class TCatalog {

    private String pkCatalogId;
    //目录名称
    private String mlmc;
    //数据项
    private String sjx;
    //目录类型
    private String mllx;
    //目录编码
    private String mlbm;
    //目录标签树
    private String mlbqs;
    //责任人
    private String zrr;
    //邮箱
    private String email;
    //是否可下载
    private Integer sfkxz;
    //是否可浏览
    private Integer sfkll;
    //是否共享
    private Integer sfgx;
    //是否开放
    private Integer sfkf;
    //共享部门范围
    private String gxbmfw;
    //数据目录描述
    private String sjmlms;
    //所属部门
    private String ssbm;

}