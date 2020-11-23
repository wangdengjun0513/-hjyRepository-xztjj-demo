package com.hjy.business.dao;

import com.hjy.business.entity.TNews;

import java.util.List;

/**
 * (TNews)表数据库访问层
 *
 * @author TNews
 * @since 2020-07-27 16:15:29
 */
public interface TNewsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkNewsId 主键
     * @return 实例对象
     */
    TNews selectById(String pkNewsId);

    /**
     * 新增数据
     *
     * @param tNews 实例对象
     * @return 影响行数
     */
    int insertSelective(TNews tNews);

    /**
     * 修改数据
     *
     * @param tNews 实例对象
     * @return 影响行数
     */
    int updateById(TNews tNews);

    /**
     * 通过主键删除数据
     *
     * @param pkNewsId 主键
     * @return 影响行数
     */
    int deleteById(String pkNewsId);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param tNews 实例对象
     * @return 对象列表
     */
    List<TNews> selectAllPage(TNews tNews);

    List<TNews> selectAllPage2(TNews tNews);

}