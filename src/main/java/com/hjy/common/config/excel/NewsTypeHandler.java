package com.hjy.common.config.excel;


import com.alibaba.fastjson.util.TypeUtils;
import com.hjy.business.entity.NewsType;

import java.util.Objects;

/**
 * @author lzy
 * @Date: 2020-04-14
 * @Time: 14:14
 */
public class NewsTypeHandler implements ExportTypeHandler {
    @Override
    public String getValue(Object type) throws Exception {
        if (Objects.isNull(type)) {
            return "";
        }
        return NewsType.findByHeaven(TypeUtils.castToInt(type)).getDesc();
    }

    @Override
    public Integer getDesc(Object desc) throws Exception {
        if (Objects.isNull(desc)) {
            return 0;
        }
        return NewsType.findByDesc(TypeUtils.castToString(desc)).getNewsType();
    }
}

