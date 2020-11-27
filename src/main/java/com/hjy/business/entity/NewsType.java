package com.hjy.business.entity;

import lombok.Getter;


/**
 * @author lzy
 * @Date: 2020-04-14
 * @Time: 11:30
 */
@Getter
public enum NewsType {

    GENERAL_NEWS(1, "普通新闻"),

    INDUSTRY_NEWS(2, "党内行业新闻"),

    COMPANY_NEWS(3, "公司党建动态"),

    SUCCESS_CASE(4, "成功案例");

    private Integer newsType;

    private String desc;

    NewsType(Integer newsType, String desc) {
        this.newsType = newsType;
        this.desc = desc;
    }

    public static NewsType findByHeaven(Integer newsType) {
        for (NewsType value : NewsType.values()) {
            if (newsType.equals(value.getNewsType())) {
                return value;
            }
        }
        throw new RuntimeException("");
    }
    public static NewsType findByDesc(String desc) {
        for (NewsType value : NewsType.values()) {
            if (desc.equals(value.getDesc())) {
                return value;
            }
        }
        throw new RuntimeException("");
    }
}

