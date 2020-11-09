package com.hjy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.utils.IDUtils;
import com.hjy.system.dao.TSysDeptMapper;
import com.hjy.system.entity.ReDeptUser;
import com.hjy.system.entity.TSysDept;
import com.hjy.system.service.TSysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TSysDept)表服务实现类
 *
 * @author makejava
 * @since 2020-09-28 09:48:46
 */
@Service
public class TSysDeptServiceImpl implements TSysDeptService {
    @Resource
    private TSysDeptMapper tSysDeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param pkDeptId 主键
     * @return 实例对象
     */
    @Override
    public TSysDept selectById(String pkDeptId) throws Exception {
        return this.tSysDeptMapper.selectById(pkDeptId);
    }

    /**
     * 新增数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    @Transactional()
    @Override
    public int insert(TSysDept tSysDept) throws Exception {
        tSysDept.setPkDeptId(IDUtils.currentTimeMillis());
        tSysDept.setCreateTime(new Date());
        tSysDept.setModifyTime(new Date());
        return tSysDeptMapper.insertSelective(tSysDept);
    }

    /**
     * 修改数据
     *
     * @param tSysDept 实例对象
     * @return 实例对象
     */
    @Transactional()
    @Override
    public int updateById(TSysDept tSysDept) throws Exception {
        return tSysDeptMapper.updateById(tSysDept);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkDeptId 主键
     * @return 是否成功
     */
    @Transactional()
    @Override
    public int deleteById(String pkDeptId){
        return tSysDeptMapper.deleteById(pkDeptId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<TSysDept> selectAll() throws Exception {
        return this.tSysDeptMapper.selectAll();
    }

    /**
     * 通过实体查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<TSysDept> selectAllByEntity(TSysDept tSysDept) throws Exception {
        return this.tSysDeptMapper.selectAllByEntity(tSysDept);
    }

    @Override
    public List<String> selectAllDeptName() {
        return tSysDeptMapper.selectAllDeptName();
    }

    @Override
    public List<String> selectDeptUser_userIded() {
        return tSysDeptMapper.selectDeptUser_userIded();
    }

    @Override
    public List<String> selectDeptUserByDept(String deptIdStr) {
        return tSysDeptMapper.selectDeptUserByDept(deptIdStr);
    }
    @Transactional()
    @Override
    public int deleteDeptUserByDeptId(String fk_dept_id) {
        return tSysDeptMapper.deleteDeptUserByDeptId(fk_dept_id);
    }

    @Transactional()
    @Override
    public int addDeptUserByList(String fk_dept_id, List<String> idList) {
        List<ReDeptUser> deptUsers = new ArrayList<>();
        for (String s:idList){
            ReDeptUser deptUser = new ReDeptUser();
            deptUser.setPk_deptUser_id(IDUtils.currentTimeMillis());
            deptUser.setFk_user_id(s);
            deptUser.setFk_dept_id(fk_dept_id);
            deptUsers.add(deptUser);
        }
        return tSysDeptMapper.addDeptUserByList(deptUsers);
    }

    @Override
    public List<TSysDept> selectAllIdAndName() {
        return tSysDeptMapper.selectAllIdAndName();
    }

    @Override
    public void addDeptUserByDeptUser(ReDeptUser deptUser) {
        tSysDeptMapper.addDeptUserByDeptUser(deptUser);
    }

    @Override
    public int deleteDeptUserByUserId(String pkUserId) {
        return tSysDeptMapper.deleteDeptUserByUserId(pkUserId);
    }

    @Override
    public String selectDeptIdByUserId(String idStr) {
        return tSysDeptMapper.selectDeptIdByUserId(idStr);
    }

    @Override
    public CommonResult addUser(String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        String fk_dept_id=String.valueOf(jsonObject.get("fk_dept_id"));
        //删除原有的部门及用户
        tSysDeptMapper.deleteDeptUserByDeptId(fk_dept_id);
        JSONArray jsonArray = jsonObject.getJSONArray("ids");
        if(jsonArray != null){
            String userIdsStr = jsonArray.toString();
            List<String> idList = JSONArray.parseArray(userIdsStr,String.class);
            //添加部门用户
            this.addDeptUserByList(fk_dept_id,idList);
        }
        return new CommonResult(200,"success","部门添加用户成功!",null);
    }
}