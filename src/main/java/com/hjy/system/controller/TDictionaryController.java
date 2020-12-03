package com.hjy.system.controller;

import com.hjy.common.domin.CommonResult;
import com.hjy.common.exception.FebsException;
import com.hjy.system.entity.TDictionary;
import com.hjy.system.service.TDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * (字典表)表控制层
 *
 * @author wangdengjun
 * @since 2020-09-28 17:48:11
 */
@Slf4j
@RestController
public class TDictionaryController {
    /**
     * 服务对象
     */
    @Autowired
    private TDictionaryService tDictionaryService;

    /**
     * 跳转到新增页面
     */
    @GetMapping(value = "/system/dictionary/addPage")
    public CommonResult tDictionaryAddPage() throws FebsException {
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
     * @param tDictionary 实体对象
     * @return 新增结果
     */
    @PostMapping("/system/dictionary/add")
    public CommonResult tDictionaryAdd(@RequestBody TDictionary tDictionary, HttpServletRequest httpRequest) throws FebsException {
        try {
            return tDictionaryService.insertSelective(tDictionary, httpRequest);
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
    @DeleteMapping("/system/dictionary/del")
    public CommonResult tDictionaryDel(@RequestBody String parm) throws FebsException {
        try {
            return tDictionaryService.deleteById(parm);
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
    @PostMapping("/system/dictionary/getOne")
    public CommonResult tDictionaryGetOne(@RequestBody String parm) throws FebsException {
        try {
            return tDictionaryService.selectById(parm);
        } catch (Exception e) {
            String message = "数据获取失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 修改数据
     *
     * @param tDictionary 实体对象
     * @return 修改结果
     */
    @PutMapping("/system/dictionary/update")
    public CommonResult tDictionaryUpdate(@RequestBody TDictionary tDictionary, HttpServletRequest httpRequest) throws FebsException {
        try {
            return tDictionaryService.updateById(tDictionary, httpRequest);
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
    @PostMapping("/system/dictionary/list")
    public CommonResult tDictionaryList(@RequestBody String param) throws FebsException {
        try {
            return tDictionaryService.selectAllPage(param);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 根据类型查询字典
     *
     * @param dicType
     * @return
     * @throws FebsException
     */
    @PostMapping("/system/dictionary/listByType")
    public CommonResult tDictionaryListByType(@RequestParam String dicType) throws FebsException {
        try {
            return tDictionaryService.selectByType(dicType);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    /**
     * 根据父级ID查询字典
     *
     * @param dicParentId
     * @return
     * @throws FebsException
     */
    @PostMapping("/system/dictionary/listByParentId")
    public CommonResult tDictionaryListByParentId(@RequestParam String dicParentId) throws FebsException {
        try {
            return tDictionaryService.selectByParentId(dicParentId);
        } catch (Exception e) {
            String message = "查询数据失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

}