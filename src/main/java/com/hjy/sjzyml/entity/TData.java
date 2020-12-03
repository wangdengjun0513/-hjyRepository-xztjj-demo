package com.hjy.sjzyml.entity;

import java.util.Date;
import lombok.Data;

/**
 * (TData)表实体类
 *
 * @author makejava
 * @since 2020-12-02 15:01:03
 */
@Data
public class TData {

    private String pkDataId;
    //信息资源编码
    private String xxzybm;
    //信息资源名称
    private String xxzymc;
    //数据项
    private String sjx;
    //多维分类
    private String dwfl;
    //标签
    private String bq;
    //面向对象
    private String mxdx;
    //单位通用名称
    private String dwtymc;
    //行政级别
    private String xzjb;
    //数据来源
    private String sjly;
    //数据采集途径
    private String sjcjtj;
    //资源共享范围
    private String zygxfw;
    //资源共享方式
    private String zygxfs;
    //资源安全级别
    private String zyaqjb;
    //数据库名称
    private String sjkmc;
    //资源产生位置
    private String zycswz;
    //资源存储位置
    private String zyccwz;
    //资源存储方式
    private String zrccfs;
    //数据上传时间
    private Date sjcjsj;

}