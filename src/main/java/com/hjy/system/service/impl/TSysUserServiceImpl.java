package com.hjy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.task.ObjectAsyncTask;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.JsonUtil;
import com.hjy.common.utils.PasswordEncryptUtils;
import com.hjy.common.utils.page.PageRequest;
import com.hjy.common.utils.page.PageResult;
import com.hjy.common.utils.page.PageUtils;
import com.hjy.system.dao.TSysRoleMapper;
import com.hjy.system.entity.ActiveUser;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.dao.TSysUserMapper;
import com.hjy.system.entity.TSysUser;
import com.hjy.system.service.TSysRoleService;
import com.hjy.system.service.TSysUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TSysUser)表服务实现类
 *
 * @author liuchun
 * @since 2020-07-24 17:05:59
 */
@Service
public class TSysUserServiceImpl implements TSysUserService {
    @Autowired
    private TSysUserMapper tSysUserMapper;
    @Autowired
    private TSysUserService tSysUserService;
    @Autowired
    private TSysRoleMapper tSysRoleMapper;
    @Autowired
    private TSysRoleService tSysRoleService;
    /**
     * 通过ID查询单条数据
     * @return 实例对象
     */
    @Override
    public TSysUser selectById(String pkUserId) throws Exception{
        return this.tSysUserMapper.selectById(pkUserId);
    }

    /**
     * 新增数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSysUser tSysUser) throws Exception{
        tSysUser.setPkUserId(IDUtils.currentTimeMillis());
        //加密
        //默认密码
        String password = "123456";
        String passwordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(tSysUser.getUsername(),password);
        tSysUser.setPassword(passwordMd5);
        tSysUser.setCreateTime(new Date());
        tSysUser.setModifyTime(new Date());
        return tSysUserMapper.insertSelective(tSysUser);
    }

    /**
     * 修改数据
     *
     * @param tSysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int updateById(TSysUser tSysUser) throws Exception{
        return tSysUserMapper.updateById(tSysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param pkUserId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String pkUserId){
        return tSysUserMapper.deleteById(pkUserId);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysUser> selectAll() throws Exception{
        return this.tSysUserMapper.selectAll();
    }
    /**
     * 通过实体查询多条数据
     * @return 对象列表
     */
    @Override
    public List<TSysUser> selectAllByEntity(TSysUser tSysUser) throws Exception{
        return this.tSysUserMapper.selectAllByEntity(tSysUser);
    }

    @Override
    public String selectUserRoleByUserId(String fk_user_id) {
        return tSysUserMapper.selectUserRoleByUserId(fk_user_id);
    }

    @Override
    public int deleteUserRoleByUserId(String fk_user_id) {
        return tSysUserMapper.deleteUserRoleByUserId(fk_user_id);
    }

    @Override
    public PageResult selectAllPage(String param) {
        JSONObject json = JSON.parseObject(param);
        //实体数据
        String pageNumStr = JsonUtil.getStringParam(json,"pageNum");
        String pageSizeStr = JsonUtil.getStringParam(json,"pageSize");
        String fullName = JsonUtil.getStringParam(json,"fullName");
        String idcard = JsonUtil.getStringParam(json,"idcard");
        String workPosition = JsonUtil.getStringParam(json,"workPosition");
        TSysUser user = new TSysUser();
        user.setFullName(fullName);
        user.setIdcard(idcard);
        user.setWorkPosition(workPosition);
        //分页记录条数
        int pageNum = 1;
        int pageSize = 10;
        if(pageNumStr != null){
            pageNum = Integer.parseInt(pageNumStr);
        }
        if(pageSizeStr != null){
            pageSize = Integer.parseInt(pageSizeStr);
        }
//        PageRequest pageRequest = new PageRequest();
//        pageRequest.setPageNum(pageNum);
//        pageRequest.setPageSize(pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TSysUser> users = tSysUserMapper.selectAllPage(user);
        return PageUtils.getPageResult(new PageInfo<TSysUser>(users));
    }

    @Override
    public int updatePassword(String parm, ActiveUser activeUser) throws Exception{
        //用户名
        String username = activeUser.getUsername();
        //数据库旧密码
        String oldPasswordMd5 = activeUser.getPassword();
        JSONObject json = JSON.parseObject(parm);
        //输入的旧密码
        String inputOldPassword = String.valueOf(json.get("oldPassword"));
        //输入的旧密码加密
        String inputOldPasswordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(username,inputOldPassword);
        if(!inputOldPasswordMd5.equals(oldPasswordMd5)){
            return 2;
        }
        //输入的新密码
        String inputNewPassword = String.valueOf(json.get("newPassword"));
        //输入的新密码加密
        String inputNewPasswordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(username,inputNewPassword);
        TSysUser user = new TSysUser();
        user.setPkUserId(activeUser.getUserId());
        user.setPassword(inputNewPasswordMd5);
        user.setModifyTime(new Date());
        return tSysUserMapper.updateById(user);
    }

    @Override
    public Map<String,Object> insertUserAndRole(String param) {
        Map<String,Object> result = new HashMap<>();
        JSONObject json = JSON.parseObject(param);
        String idcard = JsonUtil.getStringParam(json,"idcard");
        String username = JsonUtil.getStringParam(json,"username");
        List<String> usernameList = tSysUserMapper.selectAllUsername();
        if(usernameList.contains(username)){
            result.put("code",445);
            result.put("status","error");
            result.put("message","该用户名已存在，请重新输入！");
            return result;
        }
        TSysUser tSysUser = new TSysUser();
        String pkUserId = IDUtils.currentTimeMillis();
        tSysUser.setPkUserId(pkUserId);
        String fullName = JsonUtil.getStringParam(json,"fullName");
        String email = JsonUtil.getStringParam(json,"email");
        String tel = JsonUtil.getStringParam(json,"tel");
        String enableStatus = JsonUtil.getStringParam(json,"enableStatus");
        String address = JsonUtil.getStringParam(json,"address");
        String workPosition = JsonUtil.getStringParam(json,"workPosition");
        String workContent = JsonUtil.getStringParam(json,"workContent");
        tSysUser.setUsername(username);
        tSysUser.setIdcard(idcard);
        tSysUser.setFullName(fullName);
        tSysUser.setEmail(email);
        tSysUser.setTel(tel);
        tSysUser.setEnableStatus(enableStatus);
        tSysUser.setAddress(address);
        tSysUser.setWorkPosition(workPosition);
        tSysUser.setWorkContent(workContent);
        //加密
        //默认密码
        String password = "123456";
        String passwordMd5 = PasswordEncryptUtils.MyPasswordEncryptUtil(tSysUser.getUsername(),password);
        tSysUser.setPassword(passwordMd5);
        tSysUser.setCreateTime(new Date());
        tSysUser.setModifyTime(new Date());
        tSysUserMapper.insertSelective(tSysUser);
        //是否直接分配角色
        String roleId = JsonUtil.getStringParam(json,"roleId");
        if(roleId == null){
            result.put("code",201);
            result.put("status","success");
            result.put("message","添加用户成功,但暂未分配角色，无法使用！");
            return result;
        }else {
            ObjectAsyncTask.addUserRoleByUserRole(pkUserId,roleId);
            result.put("code",200);
            result.put("status","success");
            result.put("message","添加用户与分配角色成功");
            return result;
        }
    }

    @Override
    public int updateUser(String param) {
        JSONObject json = JSON.parseObject(param);
        TSysUser user = new TSysUser();
        //用户基本信息
        String pkUserId = JsonUtil.getStringParam(json,"pkUserId");
        user.setPkUserId(pkUserId);
        String username = JsonUtil.getStringParam(json,"username");
        user.setUsername(username);
        String fkDeptId = JsonUtil.getStringParam(json,"fkDeptId");
        user.setFkDeptId(fkDeptId);
        String email = JsonUtil.getStringParam(json,"email");
        user.setEmail(email);
        String tel = JsonUtil.getStringParam(json,"tel");
        user.setTel(tel);
        String IDcard = JsonUtil.getStringParam(json,"idcard");
        user.setIdcard(IDcard);
        String fullName = JsonUtil.getStringParam(json,"fullName");
        user.setFullName(fullName);
        String workPosition = JsonUtil.getStringParam(json,"workPosition");
        user.setWorkPosition(workPosition);
        String workContent = JsonUtil.getStringParam(json,"workContent");
        user.setWorkContent(workContent);
        String ip = JsonUtil.getStringParam(json,"ip");
        user.setIp(ip);
        String address = JsonUtil.getStringParam(json,"address");
        user.setAddress(address);
        String enableStatus = JsonUtil.getStringParam(json,"enableStatus");
        user.setEnableStatus(enableStatus);
        user.setModifyTime(new Date());
        String fkRoleId = JsonUtil.getStringParam(json,"roleId");
        //修改用户信息
        if(fkRoleId != null){
            tSysUserMapper.updateById(user);
            //删除原有角色
            tSysUserMapper.deleteUserRoleByUserId(pkUserId);
            ObjectAsyncTask.addUserRoleByUserRole(pkUserId,fkRoleId);
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public void addUserRoleByUserRole(ReUserRole userRole) {
        tSysRoleMapper.addUserRoleByUserRole(userRole);
    }

    @Override
    public CommonResult tSysUserDel(String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        String idStr=String.valueOf(jsonObject.get("pk_id"));
        if("1597387976992".equals(idStr)){
            return new CommonResult(445,"error","超级管理员不可删除!",null);
        }
        //删除用户表里的用户
        int i = tSysUserService.deleteById(idStr);
        //删除用户角色表里的用户
        int j = tSysUserService.deleteUserRoleByUserId(idStr);
        if(i > 0){
            return new CommonResult(200,"success","数据删除成功!",null);
        }else {
            return new CommonResult(444,"error","数据删除失败!",null);
        }
    }
}