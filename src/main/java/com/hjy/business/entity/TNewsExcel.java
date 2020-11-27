package com.hjy.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hjy.common.config.excel.ExportAnnotation;
import com.hjy.common.config.excel.ExportTypeAnnotation;
import com.hjy.common.config.excel.NewsStatusHandler;
import com.hjy.common.config.excel.NewsTypeHandler;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author scy
 * @date 2020/8/6 9:17
 */
@Data
public class TNewsExcel implements Serializable {
    private static final long serialVersionUID = 2987682842396559114L;

    @ExportAnnotation(value = "主键", col = 0)
    private String pkNewsId;
    @ExportAnnotation(value = "标题", col = 1)
    private String newsTitle;
    @ExportAnnotation(value = "内容", col = 2)
    private String newsContent;
    @ExportAnnotation(value = "状态", col = 3)
    @ExportTypeAnnotation(typeHandler = NewsStatusHandler.class)
    private String newsStatus;
    @ExportAnnotation(value = "类型", col = 4)
    @ExportTypeAnnotation(typeHandler = NewsTypeHandler.class)
    private String newsType;
    @ExportAnnotation(value = "创建时间", col = 5)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy年MM月dd日 HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}