package com.hjy.sjzyml.controller;


import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.sjzyml.entity.TCatalog;
import com.hjy.sjzyml.service.TCatalogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TCatalog)表控制层
 *
 * @author makejava
 * @since 2020-12-02 15:01:01
 */
@RestController
public class TCatalogController {
    /**
     * 服务对象
     */
    @Resource
    private TCatalogService tCatalogService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/tCatalog/addPage")
    public CommonResult insertPage() throws FebsException {
        try {
            return tCatalogService.insertPage();
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 新增数据
     *
     * @param tCatalog 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "/tCatalog/add")
    public CommonResult insert(@RequestBody TCatalog tCatalog) throws FebsException {
        try {
            return tCatalogService.insert(tCatalog);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 删除数据
     *
     * @param param json参数
     * @return 删除结果
     */
    @DeleteMapping(value = "/tCatalog/del")
    public CommonResult delete(@RequestBody String param) throws FebsException {
        try {
            return tCatalogService.delete(param);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 分页查询所有数据
     *
     * @param param json参数
     * @return 所有数据
     */
    @PostMapping(value = "/tCatalog/list")
    public CommonResult selectAll(@RequestBody String param) throws FebsException {
        try {
            return tCatalogService.selectAll(param);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param param json参数
     */
    @PostMapping(value = "/tCatalog/get")
    public CommonResult selectOne(@RequestBody String param) throws FebsException {
        try {
            return tCatalogService.selectById(param);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 修改数据
     *
     * @param tCatalog 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "/tCatalog/update")
    public CommonResult update(@RequestBody TCatalog tCatalog) throws FebsException {
        try {
            return tCatalogService.update(tCatalog);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }


}