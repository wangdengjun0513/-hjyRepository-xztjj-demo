package com.hjy.system.dao;

import com.hjy.system.entity.ReDeptUser;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.entity.TSysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TSysDept)表数据库访问层
 *
 * @author liuchun
 * @since 2020-07-27 16:15:29
 */
public interface TSysDeptMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param pkDeptId 主键
     * @return 实例对象
     */
    TSysDept selectById(String pkDeptId);

    /**
     * 新增数据
     *
     * @param tSysDept 实例对象
     * @return 影响行数
     */
    int insertSelective(TSysDept tSysDept);

    /**
     * 修改数据
     *
     * @param tSysDept 实例对象
     * @return 影响行数
     */
    int updateById(TSysDept tSysDept);

    /**
     * 通过主键删除数据
     *
     * @param pkDeptId 主键
     * @return 影响行数
     */
    int deleteById(String pkDeptId);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<TSysDept> selectAll();
     /**
     * 通过实体作为筛选条件查询
     *
     * @param tSysDept 实例对象
     * @return 对象列表
     */
    List<TSysDept> selectAllByEntity(TSysDept tSysDept);


    List<String> selectAllDeptName();

    List<String> selectDeptUser_userIded();

    List<String> selectDeptUserByDept(@Param("fkDeptId")String deptIdStr);

    int deleteDeptUserByDeptId(@Param("fkDeptId")String fk_dept_id);

    /**
     * 批量添加部门用户
     */
    int addDeptUserByList(@Param("idList")List<ReDeptUser> idList);

    List<TSysDept> selectAllIdAndName();

    int addDeptUserByDeptUser(ReDeptUser deptUser);

    int deleteDeptUserByUserId(@Param("fkUserId")String fkUserId);

    String selectDeptIdByUserId(@Param("fkUserId")String fkUserId);
}