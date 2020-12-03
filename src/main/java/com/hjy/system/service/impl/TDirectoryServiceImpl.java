package com.hjy.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.JsonUtil;
import com.hjy.common.utils.StringUtil;
import com.hjy.common.utils.page.PageUtils;
import com.hjy.system.dao.TDictionaryMapper;
import com.hjy.system.entity.TDictionary;
import com.hjy.system.service.ShiroService;
import com.hjy.system.service.TDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * (字典表)表服务实现类
 *
 * @author wangdengjun
 * @since 2020-07-27 16:15:29
 */
@Service
public class TDirectoryServiceImpl implements TDictionaryService {
    @Autowired
    private TDictionaryMapper tDictionaryMapper;
    @Autowired
    private ShiroService shiroService;

    /**
     * 通过ID查询单条数据
     *
     * @param parm 主键
     * @return 实例对象
     */
    @Override
    public CommonResult selectById(String parm) {
        JSONObject jsonObject = JSON.parseObject(parm);
        String pkDicId = String.valueOf(jsonObject.get("pkDicId"));
        TDictionary tDictionary = tDictionaryMapper.selectById(pkDicId);
        return new CommonResult(200, "success", "数据获取成功!", tDictionary);
    }

    /**
     * 新增数据
     *
     * @param tDictionary
     * @return
     * @throws Exception
     */
    @Override
    public CommonResult insertSelective(TDictionary tDictionary, HttpServletRequest httpRequest) {
        tDictionary.setPkDicId(IDUtils.getUUID());
        tDictionary.setDicUpdateTime(new Date());
        tDictionaryMapper.insertSelective(tDictionary);
        return new CommonResult(200, "success", "数据添加成功!", null);
    }

    /**
     * 修改数据
     *
     * @param tDictionary
     * @return
     * @throws Exception
     */
    @Override
    public CommonResult updateById(TDictionary tDictionary, HttpServletRequest httpRequest) {
        tDictionary.setDicUpdateTime(new Date());
        tDictionaryMapper.updateById(tDictionary);
        return new CommonResult(200, "success", "数据修改成功!", null);
    }

    /**
     * 通过主键删除数据
     *
     * @param parm 主键
     * @return 是否成功
     */
    @Override
    public CommonResult deleteById(String parm) {
        JSONObject jsonObject = JSON.parseObject(parm);
        String pkDicId = String.valueOf(jsonObject.get("pkDicId"));
        tDictionaryMapper.deleteById(pkDicId);
        return new CommonResult(200, "success", "数据删除成功!", null);
    }


    @Override
    public CommonResult selectAllPage(String param) {
        JSONObject json = JSON.parseObject(param);
        String pageNumStr = JsonUtil.getStringParam(json, "pageNum");
        String pageSizeStr = JsonUtil.getStringParam(json, "pageSize");
        String dicCode = JsonUtil.getStringParam(json, "dicCode");
        String dicName = JsonUtil.getStringParam(json, "dicName");
        String dicType = JsonUtil.getStringParam(json, "dicType");
        String dicStatus = JsonUtil.getStringParam(json, "dicStatus");
        TDictionary tDictionary = new TDictionary();
        if (StringUtil.isNotEmptyAndNull(dicStatus)) {
            tDictionary.setDicStatus(Integer.parseInt(dicStatus));
        }
        tDictionary.setDicCode(dicCode);
        tDictionary.setDicName(dicName);
        tDictionary.setDicType(dicType);
        //分页记录条数
        int pageNum = 1;
        int pageSize = 10;
        if (pageNumStr != null) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        if (pageSizeStr != null) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<TDictionary> tDictionarys = tDictionaryMapper.selectAllPage(tDictionary);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PageResult", PageUtils.getPageResult(new PageInfo<TDictionary>(tDictionarys)));
        return new CommonResult(200, "success", "查询数据成功!", jsonObject);
    }

    @Override
    public CommonResult selectByType(String dicType) {
        TDictionary tDictionary = new TDictionary();
        tDictionary.setDicType(dicType);
        return new CommonResult(200, "success", "查询数据成功!", tDictionaryMapper.selectAllPage(tDictionary));
    }

    @Override
    public CommonResult selectByParentId(String dicParentId) {
        TDictionary tDictionary = new TDictionary();
        tDictionary.setDicParentId(dicParentId);
        return new CommonResult(200, "success", "查询数据成功!", tDictionaryMapper.selectAllPage(tDictionary));
    }

}