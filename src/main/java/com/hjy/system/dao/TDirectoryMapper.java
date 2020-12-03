package com.hjy.system.dao;

import com.hjy.system.entity.TDictionary;

import java.util.List;

/**
 * (字典表)表数据库访问层
 *
 * @author TDictionary
 * @since 2020-07-27 16:15:29
 */
public interface TDirectoryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkDicId 主键
     * @return 实例对象
     */
    TDictionary selectById(String pkDicId);

    /**
     * 新增数据
     *
     * @param tDictionary 实例对象
     * @return 影响行数
     */
    int insertSelective(TDictionary tDictionary);

    /**
     * 修改数据
     *
     * @param tDictionary 实例对象
     * @return 影响行数
     */
    int updateById(TDictionary tDictionary);

    /**
     * 通过主键删除数据
     *
     * @param pkDicId 主键
     * @return 影响行数
     */
    int deleteById(String pkDicId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDictionary 实例对象
     * @return 对象列表
     */
    List<TDictionary> selectAllPage(TDictionary tDictionary);

    /**
     * 根据类型查询字典
     *
     * @param dicType
     * @return
     */
    List<TDictionary> selectByType(String dicType);

    /**
     * 根据父级ID查询字典
     *
     * @param dicParentId
     * @return
     */
    List<TDictionary> selectByParentId(String dicParentId);
}