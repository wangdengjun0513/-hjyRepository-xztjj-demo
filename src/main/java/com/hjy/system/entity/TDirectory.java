package com.hjy.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (名录库)实体类
 *
 * @author wangdengjun
 * @since 2020-09-28 17:48:11
 */
@Data
public class TDirectory implements Serializable {
    private static final long serialVersionUID = 4784668379101181742L;
    /**
     * 主键ID
     */
    private String pkDirId;
    /**
     * 名称
     */
    private String dirName;
    /**
     * 简称
     */
    private String dirShortName;
    /**
     * 法人
     */
    private String dirLegalPerson;
    /**
     * 注册资本
     */
    private String dirRegisteredCapital;
    /**
     * 成立日期
     */
    private Date dirDateEst;
}