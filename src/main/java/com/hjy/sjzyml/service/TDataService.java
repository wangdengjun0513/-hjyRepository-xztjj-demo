package com.hjy.sjzyml.service;

import com.hjy.common.domin.CommonResult;
import com.hjy.sjzyml.entity.TData;

/**
 * (TData)表服务接口
 *
 * @author makejava
 * @since 2020-12-02 15:01:03
 */
public interface TDataService {
    /**
     * 添加前获取数据
     *
     * @return
     */
    CommonResult insertPage();

    /**
     * 添加数据
     *
     * @param tData
     * @return
     */
    CommonResult insert(TData tData);

    /**
     * 修改数据
     *
     * @param tData
     * @return
     */
    CommonResult update(TData tData);

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