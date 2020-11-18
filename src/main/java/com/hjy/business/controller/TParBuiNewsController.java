package com.hjy.business.controller;

import com.hjy.business.entity.TNews;
import com.hjy.business.service.TNewsService;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * (TNews)表控制层
 *
 * @author wangdengjun
 * @since 2020-09-28 17:48:11
 */
@Slf4j
@RestController
public class TParBuiNewsController {
    /**
     * 服务对象
     */
    @Autowired
    private TNewsService tNewsService;

    /**
     * 跳转到新增页面
     */
    @GetMapping(value = "/business/parbuinews/addPage")
    public CommonResult tNewsAddPage() throws FebsException {
        try {
            return new CommonResult(200, "success", "成功!", null);
        } catch (Exception e) {
            String message = "失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 新增数据
     *
     * @param tNews 实体对象
     * @return 新增结果
     */
    @RequiresPermissions({"parbuinews:add"})
    @PostMapping("/business/parbuinews/add")
    public CommonResult tNewsAdd(@RequestBody TNews tNews, HttpServletRequest httpRequest) throws FebsException {
        try {
            return tNewsService.insertSelective(tNews, httpRequest, tNews.getNewsType());
        } catch (Exception e) {
            String message = "数据添加失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @RequiresPermissions({"parbuinews:del"})
    @DeleteMapping("/business/parbuinews/del")
    public CommonResult tNewsDel(@RequestBody String parm) throws FebsException {
        try {
            return tNewsService.deleteById(parm);
        } catch (Exception e) {
            String message = "数据删除失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @PostMapping("/business/parbuinews/getOne")
    public CommonResult tNewsGetOne(@RequestBody String parm) throws FebsException {
        try {
            return tNewsService.selectById(parm);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 修改数据
     *
     * @param tNews 实体对象
     * @return 修改结果
     */
    @RequiresPermissions({"parbuinews:update"})
    @PutMapping("/business/parbuinews/update")
    public CommonResult tNewsUpdate(@RequestBody TNews tNews, HttpServletRequest httpRequest) throws FebsException {
        try {
            return tNewsService.updateById(tNews, httpRequest);
        } catch (Exception e) {
            String message = "修改失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @PostMapping("/business/parbuinews/list")
    public CommonResult tNewsList(@RequestBody String param) throws FebsException {
        try {
            return tNewsService.selectAllPage(param, 0);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}