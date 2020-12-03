package com.hjy.system.service;

import com.hjy.common.domin.CommonResult;
import com.hjy.system.entity.TDictionary;

import javax.servlet.http.HttpServletRequest;

/**
 * (字典表)表服务接口
 *
 * @author wangdengjun
 * @since 2020-07-27 16:15:29
 */
public interface TDictionaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param parm 主键
     * @return 实例对象
     */
    CommonResult selectById(String parm);


    /**
     * 新增数据
     *
     * @param tDictionary
     * @return
     * @
     */
    CommonResult insertSelective(TDictionary tDictionary, HttpServletRequest httpRequest);


    /**
     * 修改数据
     *
     * @param tDictionary
     * @return
     * @
     */
    CommonResult updateById(TDictionary tDictionary, HttpServletRequest httpRequest);

    /**
     * 通过主键删除数据
     *
     * @param parm 主键
     * @return 是否成功
     */
    CommonResult deleteById(String parm);

    /**
     * @param param
     * @return
     */
    CommonResult selectAllPage(String param);

    CommonResult selectByType(String dicType);

    CommonResult selectByParentId(String dicParentId);
}