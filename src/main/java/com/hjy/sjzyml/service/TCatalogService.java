package com.hjy.sjzyml.service;

import com.hjy.common.domin.CommonResult;
import com.hjy.sjzyml.entity.TCatalog;

/**
 * (TCatalog)表服务接口
 *
 * @author makejava
 * @since 2020-12-02 15:01:01
 */
public interface TCatalogService {
    /**
     * 添加前获取数据
     *
     * @return
     */
    CommonResult insertPage();

    /**
     * 添加数据
     *
     * @param tCatalog
     * @return
     */
    CommonResult insert(TCatalog tCatalog);

    /**
     * 修改数据
     *
     * @param tCatalog
     * @return
     */
    CommonResult update(TCatalog tCatalog);

    /**
     * 删除数据
     *
     * @param param
     * @return
     */
    CommonResult delete(String param);

    /**
     * 查询所有数据
     *
     * @param param
     * @return
     */
    CommonResult selectAll(String param);

    /**
     * 获取单个数据
     *
     * @param param
     * @return
     */
    CommonResult selectById(String param);
}