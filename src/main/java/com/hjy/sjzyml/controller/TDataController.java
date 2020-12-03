package com.hjy.sjzyml.controller;


import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.sjzyml.entity.TData;
import com.hjy.sjzyml.service.TDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TData)表控制层
 *
 * @author makejava
 * @since 2020-12-02 15:01:03
 */
@RestController
public class TDataController {
    /**
     * 服务对象
     */
    @Resource
    private TDataService tDataService;

    /**
     * 1 跳转到新增页面
     */
    @GetMapping(value = "/tData/addPage")
    public CommonResult insertPage() throws FebsException {
        try {
            return tDataService.insertPage();
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 新增数据
     *
     * @param tData 实体对象
     * @return 新增结果
     */
    @PostMapping(value = "/tData/add")
    public CommonResult insert(@RequestBody TData tData) throws FebsException {
        try {
            return tDataService.insert(tData);
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
    @DeleteMapping(value = "/tData/del")
    public CommonResult delete(@RequestBody String param) throws FebsException {
        try {
            return tDataService.delete(param);
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
    @PostMapping(value = "/tData/list")
    public CommonResult selectAll(@RequestBody String param) throws FebsException {
        try {
            return tDataService.selectAll(param);
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
    @PostMapping(value = "/tData/get")
    public CommonResult selectOne(@RequestBody String param) throws FebsException {
        try {
            return tDataService.selectById(param);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }

    /**
     * 修改数据
     *
     * @param tData 实体对象
     * @return 修改结果
     */
    @PutMapping(value = "/tData/update")
    public CommonResult update(@RequestBody TData tData) throws FebsException {
        try {
            return tDataService.update(tData);
        } catch (Exception e) {
            String message = "失败";
            throw new FebsException(message);
        }
    }


}