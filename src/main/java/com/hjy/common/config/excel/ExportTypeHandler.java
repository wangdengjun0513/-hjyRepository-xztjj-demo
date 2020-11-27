package com.hjy.common.config.excel;


/**
 * @author lzy
 * @Date: 2020-04-14
 * @Time: 10:52
 */
public interface ExportTypeHandler {

    /**
     * 根据传入的type获取其对应的类型
     *
     * @param type
     * @return
     * @throws Exception 转换类型错误 或者 找不到type对应的desc
     */
    String getValue(Object type) throws Exception;


    Integer getDesc(Object desc) throws Exception;
}
