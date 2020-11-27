package com.hjy.business.controller;

import com.hjy.business.entity.TNewsExcel;
import com.hjy.business.service.TNewsService;
import com.hjy.common.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * (TBanner)表控制层
 *
 * @author wangdengjun
 * @since 2020-09-28 17:48:11
 */
@Slf4j
@RestController
public class TestExcelController {
    @Autowired
    private TNewsService tNewsService;

    /**
     * 导入公司信息
     */
    @RequestMapping(value = "/excel/importExcel", method = RequestMethod.POST)
    public void importExcel(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        //导入转换成实体的list
        List<TNewsExcel> userModelList = ExcelUtils.readExcel("", TNewsExcel.class, file);
        System.out.println(userModelList);
        //转成list后处理自己的业务
    }

    /**
     * 导出操作
     *
     * @return
     */
    @RequestMapping(value = "/excel/exportExcel", method = RequestMethod.GET)
    public void importExcel(HttpServletResponse response) {
        List<TNewsExcel> tNewsExcelList = tNewsService.getTNewsExcelList();
        ExcelUtils.writeExcel(response, tNewsExcelList, TNewsExcel.class, "导出文件名称.xlsx");
    }

}