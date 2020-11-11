package com.hjy.common.task;

import com.hjy.common.utils.IDUtils;
import com.hjy.system.entity.ReDeptUser;
import com.hjy.system.entity.ReUserRole;
import com.hjy.system.service.TSysDeptService;
import com.hjy.system.service.TSysParamService;
import com.hjy.system.service.TSysRoleService;
import com.hjy.system.service.TSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

//@Component
//@Async
public class ObjectAsyncTask {
    @Autowired
    private TSysParamService tSysParamService;
    @Autowired
    private TSysUserService tSysUserService;
    @Autowired
    private TSysRoleService tSysRoleService;
    @Autowired
    private TSysDeptService tSysDeptService;

    private static ObjectAsyncTask ntClient;

    public static void addUserRoleByUserRole(String pkUserId, String roleId) {
        ReUserRole userRole = new ReUserRole();
        userRole.setPk_userRole_id(IDUtils.currentTimeMillis());
        userRole.setFk_user_id(pkUserId);
        userRole.setFk_role_id(roleId);
        ntClient.tSysUserService.addUserRoleByUserRole(userRole);
    }

    public static void deleteRolePermsByRoleId(String fk_role_id) {
        ntClient.tSysRoleService.deleteRolePermsByRoleId(fk_role_id);
    }

    //添加角色默认的权限-即主页的3个
    public static void addDefultRoelPerms(String fk_role_id) {
        List<String> idList = new ArrayList<>();
        idList.add("1596706636946");
        idList.add("1596706882298");
        idList.add("1596707062416");
        ntClient.tSysRoleService.distributeMenu(fk_role_id,idList);
    }

    public static void addDeptUserByDeptUser(String pkUserId, String deptId) {
        ReDeptUser deptUser = new ReDeptUser();
        deptUser.setPk_deptUser_id(IDUtils.currentTimeMillis());
        deptUser.setFk_user_id(pkUserId);
        deptUser.setFk_dept_id(deptId);
        ntClient.tSysDeptService.addDeptUserByDeptUser(deptUser);
    }

    //初始化所有服务
    @PostConstruct
    public void init() {
        ntClient = this;
        ntClient.tSysParamService = this.tSysParamService;
        ntClient.tSysUserService = this.tSysUserService;
        ntClient.tSysRoleService = this.tSysRoleService;
        ntClient.tSysDeptService = this.tSysDeptService;
    }
}
