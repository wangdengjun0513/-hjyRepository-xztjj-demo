package com.hjy.business.entity;

import lombok.Getter;


/**
 * @author lzy
 * @Date: 2020-04-14
 * @Time: 11:30
 */
@Getter
public enum NewsStatus {

    ONLINE(1, "上线"),

    OFFLINE(2, "下线");


    private Integer newsStatus;

    private String desc;

    NewsStatus(Integer newsStatus, String desc) {
        this.newsStatus = newsStatus;
        this.desc = desc;
    }

    public static NewsStatus findByHeaven(Integer newsStatus) {
        for (NewsStatus value : NewsStatus.values()) {
            if (newsStatus.equals(value.getNewsStatus())) {
                return value;
            }
        }
        throw new RuntimeException("");
    }
    public static NewsStatus findByDesc(String desc) {
        for (NewsStatus value : NewsStatus.values()) {
            if (desc.equals(value.getDesc())) {
                return value;
            }
        }
        throw new RuntimeException("");
    }
}

