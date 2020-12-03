//package com.hjy.system.controller;
//
//import com.hjy.common.domin.CommonResult;
//import com.hjy.common.exception.FebsException;
//import com.hjy.system.entity.TDirectory;
//import com.hjy.system.service.TDirectoryService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * (字典表)表控制层
// *
// * @author wangdengjun
// * @since 2020-09-28 17:48:11
// */
//@Slf4j
//@RestController
//public class TDirectoryController {
//    /**
//     * 服务对象
//     */
//    @Autowired
//    private TDirectoryService tDirectoryService;
//
//    /**
//     * 跳转到新增页面
//     */
//    @GetMapping(value = "/system/directory/addPage")
//    public CommonResult tDirectoryAddPage() throws FebsException {
//        try {
//            return new CommonResult(200, "success", "成功!", null);
//        } catch (Exception e) {
//            String message = "失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param tDirectory 实体对象
//     * @return 新增结果
//     */
//    @PostMapping("/system/directory/add")
//    public CommonResult tDirectoryAdd(@RequestBody TDirectory tDirectory, HttpServletRequest httpRequest) throws FebsException {
//        try {
//            return tDirectoryService.insertSelective(tDirectory, httpRequest);
//        } catch (Exception e) {
//            String message = "数据添加失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 删除数据
//     *
//     * @return 删除结果
//     */
//    @DeleteMapping("/system/directory/del")
//    public CommonResult tDirectoryDel(@RequestBody String parm) throws FebsException {
//        try {
//            return tDirectoryService.deleteById(parm);
//        } catch (Exception e) {
//            String message = "数据删除失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @return 单条数据
//     */
//    @PostMapping("/system/directory/getOne")
//    public CommonResult tDirectoryGetOne(@RequestBody String parm) throws FebsException {
//        try {
//            return tDirectoryService.selectById(parm);
//        } catch (Exception e) {
//            String message = "数据获取失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param tDirectory 实体对象
//     * @return 修改结果
//     */
//    @PutMapping("/system/directory/update")
//    public CommonResult tDirectoryUpdate(@RequestBody TDirectory tDirectory, HttpServletRequest httpRequest) throws FebsException {
//        try {
//            return tDirectoryService.updateById(tDirectory, httpRequest);
//        } catch (Exception e) {
//            String message = "修改失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 查询所有数据
//     *
//     * @return 所有数据
//     */
//    @PostMapping("/system/directory/list")
//    public CommonResult tDirectoryList(@RequestBody String param) throws FebsException {
//        try {
//            return tDirectoryService.selectAllPage(param);
//        } catch (Exception e) {
//            String message = "查询数据失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 根据类型查询字典
//     *
//     * @param dicType
//     * @return
//     * @throws FebsException
//     */
//    @PostMapping("/system/directory/listByType")
//    public CommonResult tDirectoryListByType(@RequestParam String dicType) throws FebsException {
//        try {
//            return tDirectoryService.selectByType(dicType);
//        } catch (Exception e) {
//            String message = "查询数据失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    /**
//     * 根据父级ID查询字典
//     *
//     * @param dicParentId
//     * @return
//     * @throws FebsException
//     */
//    @PostMapping("/system/directory/listByParentId")
//    public CommonResult tDirectoryListByParentId(@RequestParam String dicParentId) throws FebsException {
//        try {
//            return tDirectoryService.selectByParentId(dicParentId);
//        } catch (Exception e) {
//            String message = "查询数据失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//}