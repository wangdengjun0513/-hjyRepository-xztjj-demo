package com.hjy.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TNews)实体类
 *
 * @author wangdengjun
 * @since 2020-09-28 17:48:11
 */
@Data
public class TNews implements Serializable {
    private static final long serialVersionUID = -2654819087415453966L;
    /**
     * 新闻主键id
     */
    private String pkNewsId;
    /**
     * 新闻标题
     */
    private String newsTitle;
    /**
     * 新闻内容
     */
    private String newsContent;
    /**
     * 新闻封面
     */
    private String newsCover;
    /**
     * 新闻状态1已上线2未上线
     */
    private int newsStatus;
    /**
     * 新闻类型 1普通新闻2党内行业新闻3公司党建动态4成功案例
     */
    private int newsType;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createDate;
    /**
     * 创建人id
     */
    private String createUserId;
    /**
     * 创建人姓名
     */
    private String createUseFullName;
    /**
     * 最后修改人id
     */
    private String lastModifyUserId;
    /**
     * 最后修改人姓名
     */
    private String lastModifyUserFullName;
    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date lastModifyDate;
}