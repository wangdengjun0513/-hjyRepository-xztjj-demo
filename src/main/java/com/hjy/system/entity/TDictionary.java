package com.hjy.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (字典表)实体类
 *
 * @author wangdengjun
 * @since 2020-09-28 17:48:11
 */
@Data
public class TDictionary implements Serializable {
    private static final long serialVersionUID = -131263292398670467L;
    /**
     * 主键ID
     */
    private String pkDicId;
    /**
     * 父级ID
     */
    private String dicParentId;
    /**
     * 编码
     */
    private String dicCode;
    /**
     * 名称/值
     */
    private String dicName;
    /**
     * 类别
     */
    private String dicType;
    /**
     * 级别
     */
    private int dicLevel;
    /**
     * 排序
     */
    private int dicSort;
    /**
     * 状态 1上线2下线
     */
    private int dicStatus;
    /**
     * 更新时间
     */
    private Date dicUpdateTime;
    /**
     * 描述
     */
    private String dicDesc;
}