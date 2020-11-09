package com.hjy.system.service;

import com.hjy.common.domin.CommonResult;
import com.hjy.system.entity.ReDeptUser;
import com.hjy.system.entity.TSysDept;

import java.util.List;

/**
 * (TSysDept)表服务接口
 *
 * @author makejava
 * @since 2020-09-28 09:48:46
 */
public interface TSysDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param pkDeptId 主键
     * @return 实例对象
     */
    TSysDept selectById(String pkDeptId) throws Exception;


    /**
     * 新增数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    int insert(TSysDept tSysDept) throws Exception;

    /**
     * 修改数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    int updateById(TSysDept tSysDept) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param pkDeptId 主键
     * @return 是否成功
     */
    int deleteById(String pkDeptId);

    /**
     * 查询所有数据
     *
     * @return list
     */
    List<TSysDept> selectAll() throws Exception;

    /**
     * 通过实体查询所有数据
     *
     * @return list
     */
    List<TSysDept> selectAllByEntity(TSysDept tSysDept) throws Exception;

    List<String> selectAllDeptName();

    List<String> selectDeptUser_userIded();

    List<String> selectDeptUserByDept(String deptIdStr);
    //删除原有的部门及用户角色
    int deleteDeptUserByDeptId(String fk_dept_id);
    //添加部门用户
    int addDeptUserByList(String fk_dept_id, List<String> idList);

    List<TSysDept> selectAllIdAndName();

    void addDeptUserByDeptUser(ReDeptUser deptUser);
    //删除该用户的已分配的部门
    int deleteDeptUserByUserId(String pkUserId);

    String selectDeptIdByUserId(String idStr);

    CommonResult addUser(String param);
//
//    CommonResult deptDel(String param);
}