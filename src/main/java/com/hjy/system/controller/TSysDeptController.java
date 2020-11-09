package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.common.utils.JsonUtil;
import com.hjy.system.entity.TSysDept;
import com.hjy.system.entity.TSysRole;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.TSysDeptService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjy.system.service.TSysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * (TSysDept)表控制层
 *
 * @author liuchun
 * @since 2020-07-27 16:15:29
 */
@Slf4j
@RestController
public class TSysDeptController {
    /**
     * 服务对象
     */
    @Autowired
    private TSysDeptService tSysDeptService;
    @Autowired
    private TSysUserService tSysUserService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/system/dept/addPage")
    public CommonResult tSysDeptAddPage() throws FebsException {
        try {
            //
            List<TSysDept> list = tSysDeptService.selectAllIdAndName();
            return new CommonResult(200,"success","成功!",list);
        } catch (Exception e) {
            String message = "失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 1 新增数据
     * @param tSysDept 实体对象
     * @return 新增结果
     */
    @RequiresPermissions({"dept:add"})
    @PostMapping("/system/dept/add")
    public CommonResult tSysDeptAdd(@RequestBody TSysDept tSysDept) throws FebsException{
        try {
            //
            tSysDeptService.insert(tSysDept);
            return new CommonResult(200,"success","数据添加成功!",null);
        } catch (Exception e) {
            String message = "数据添加失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 2 查询所有数据
     * @return 所有数据
     */
    @RequiresPermissions({"dept:view"})
    @PostMapping("/system/dept/list")
    public CommonResult tSysDeptList() throws FebsException{
        try {
            //
            List<TSysDept> tSysDeptList = tSysDeptService.selectAll();
            return new CommonResult(200,"success","查询数据成功!",tSysDeptList);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 3 删除数据
     * @return 删除结果
     */
    @RequiresPermissions({"dept:del"})
    @DeleteMapping("/system/dept/del")
    public CommonResult tSysDeptDel(@RequestBody String parm) throws FebsException{
        JSONObject jsonObject = JSON.parseObject(parm);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        try {
            //
            tSysDeptService.deleteById(idStr);
            return new CommonResult(200,"success","数据删除成功!",null);
        } catch (Exception e) {
            String message = "数据删除失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 6 给部门下发用户
     */
    @PostMapping("/system/dept/addUserUI")
    public CommonResult systemRoleAddUserUI(@RequestBody String parm) throws FebsException{
        JSONObject json = JSON.parseObject(parm);
        String deptIdStr=String.valueOf(json.get("fk_dept_id"));
        JSONObject jsonObject = new JSONObject();
        try {
            //通过deptIdStr查找部门
            TSysDept tSysDept = tSysDeptService.selectById(deptIdStr);
            jsonObject.put("dept",tSysDept);
            //查找所有用户
            List<TSysUser> tSysUserList = tSysUserService.selectAll();
            jsonObject.put("userList",tSysUserList);
            //查询已分配的用户部门并进行回显
            List<String> deptUserList = tSysDeptService.selectDeptUser_userIded();
            List<String> deptUserList2 = tSysDeptService.selectDeptUserByDept(deptIdStr);
            jsonObject.put("ids",deptUserList);
            jsonObject.put("idsFP",deptUserList2);
            return new CommonResult(200,"success","获取部门已分配用户成功!",jsonObject);
        } catch (Exception e) {
            String message = "获取部门已分配用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 6 给部门下发用户
     */
    @RequiresPermissions({"dept:addUser"})
    @PostMapping("/system/dept/addUser")
    public CommonResult systemRoleAddUser(@RequestBody String param) throws FebsException{
        try {
            return tSysDeptService.addUser(param);
        } catch (Exception e) {
            String message = "部门添加用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
    /**
     * 4 通过主键查询单条数据
     * @return 单条数据
     */
    @PostMapping("/system/dept/getOne")
    public CommonResult tSysDeptgetOne(@RequestBody String param) throws FebsException{
        JSONObject json = JSON.parseObject(param);
        String idStr= JsonUtil.getStringParam(json,"pk_id");
        try {
            //
            JSONObject jsonObject = new JSONObject();
            TSysDept tSysDept = tSysDeptService.selectById(idStr);
            jsonObject.put("dept",tSysDept);
            List<TSysDept> list = tSysDeptService.selectAllIdAndName();
            jsonObject.put("depts",list);
            return new CommonResult(200,"success","数据获取成功!",jsonObject);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 4 修改数据
     * @param tSysDept 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"dept:update"})
    @PutMapping("/system/dept/update")
    public CommonResult tSysDeptUpdate(@RequestBody TSysDept tSysDept) throws FebsException{
        try {
            //
            tSysDeptService.updateById(tSysDept);
            TSysDept tSysDept2 = tSysDeptService.selectById(tSysDept.getPkDeptId());
            return new CommonResult(200,"success","修改成功!",tSysDept2);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}