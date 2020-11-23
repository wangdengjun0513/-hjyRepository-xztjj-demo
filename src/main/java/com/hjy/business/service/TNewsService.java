package com.hjy.business.service;

import com.hjy.business.entity.TNews;
import com.hjy.common.domin.CommonResult;

import javax.servlet.http.HttpServletRequest;

/**
 * (TNews)表服务接口
 *
 * @author wangdengjun
 * @since 2020-07-27 16:15:29
 */
public interface TNewsService {

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
     * @param tNews
     * @param newsType
     * @return
     * @
     */
    CommonResult insertSelective(TNews tNews, HttpServletRequest httpRequest, Integer newsType);

    /**
     * 修改数据
     *
     * @param tNews
     * @return
     * @
     */
    CommonResult updateById(TNews tNews, HttpServletRequest httpRequest);

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
    CommonResult selectAllPage(String param, Integer newsType);

    CommonResult selectAllPage2(String param, Integer newsType);

}