package com.hjy.sjzyml.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hjy.common.domin.CommonResult;
import com.hjy.common.utils.IDUtils;
import com.hjy.common.utils.JsonUtil;
import com.hjy.common.utils.page.PageResult;
import com.hjy.common.utils.page.PageUtils;
import com.hjy.sjzyml.dao.ReCatalogDataMapper;
import com.hjy.sjzyml.dao.TCatalogMapper;
import com.hjy.sjzyml.entity.ReCatalogData;
import com.hjy.sjzyml.entity.TCatalog;
import com.hjy.sjzyml.service.TCatalogService;
import com.hjy.system.entity.TSysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TCatalog)表服务实现类
 *
 * @author makejava
 * @since 2020-12-02 15:01:01
 */
@Service("tCatalogService")
public class TCatalogServiceImpl implements TCatalogService {

    @Resource
    private TCatalogMapper tCatalogMapper;
    @Resource
    private ReCatalogDataMapper reCatalogDataMapper;

    /**
     * 添加前获取数据
     * @return
     */
    @Override
    public CommonResult insertPage() {
        return new CommonResult(200,"success","",null);
    }

    /**
     * 添加数据
     * @param tCatalog
     * @return
     */
    @Transactional()
    @Override
    public CommonResult insert(TCatalog tCatalog) {
        String pkCatalogId = IDUtils.getUUID();
        tCatalog.setPkCatalogId(pkCatalogId);
        //添加关联数据项
        ReCatalogData reCatalogData = new ReCatalogData();
        reCatalogData.setPkCatalogdataId(IDUtils.getUUID());
        reCatalogData.setFkCatalogId(pkCatalogId);
        reCatalogData.setFkDataId(tCatalog.getSjx());
        int j = reCatalogDataMapper.insertSelective(reCatalogData);
        //添加资源目录数据
        int i = tCatalogMapper.insertSelective(tCatalog);
        if (i > 0) {
            return new CommonResult(200,"success","目录数据添加成功！",tCatalog);
        } else {
            return new CommonResult(444,"error","目录数据添加失败！",null);
        }
    }

    /**
     * 修改数据
     * @param tCatalog
     * @return
     */
    @Override
    public CommonResult update(TCatalog tCatalog) {
        //修改主数据
        int i = tCatalogMapper.update(tCatalog);
        if (i > 0) {
            return new CommonResult(200,"success","数据修改成功",tCatalog);
        } else {
            return new CommonResult(444,"error","数据修改失败",null);
        }
    }

    /**
     * 删除数据
     * @param param
     * @return
     */
    @Override
    public CommonResult delete(String param) {
        JSONObject json = JSON.parseObject(param);
        String pk_id = JsonUtil.getStringParam(json,"pk_id");
        int i = tCatalogMapper.deleteById(pk_id);
        if (i > 0) {
            return new CommonResult(200,"success","数据删除成功!",null);
        } else {
            return new CommonResult(444,"error","数据删除失败!",null);
        }
    }

    /**
     * 查询所有数据
     * @param param
     * @return
     */
    @Override
    public CommonResult selectAll(String param) {
        PageResult pageResult= this.selectAllPage(param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PageResult",pageResult);
        return new CommonResult(200,"success","查询数据成功!",jsonObject);
    }

    /**
     * 获取单个数据
     *
     * @param param
     * @return
     */
    @Override
    public CommonResult selectById(String param) {
        JSONObject json = JSON.parseObject(param);
        //实体数据
        String pk_id = JsonUtil.getStringParam(json, "pk_id");
        TCatalog catalog = tCatalogMapper.selectByPkId(pk_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PageResult",catalog);
        return new CommonResult(200,"success","查询数据成功!",jsonObject);

    }
    public PageResult selectAllPage(String param) {
        JSONObject json = JSON.parseObject(param);
        //实体数据
        String pageNumStr = JsonUtil.getStringParam(json, "pageNum");
        String pageSizeStr = JsonUtil.getStringParam(json, "pageSize");
        String mlmc = JsonUtil.getStringParam(json, "mlmc");
        String mllx = JsonUtil.getStringParam(json, "mllx");
        String deptId = JsonUtil.getStringParam(json, "deptId");
        TCatalog catalog = new TCatalog();
        catalog.setMlmc(mlmc);
        catalog.setMllx(mllx);
        catalog.setSsbm(deptId);
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
        List<TCatalog> catalogs = tCatalogMapper.selectAllPage(catalog);
        return PageUtils.getPageResult(new PageInfo<TCatalog>(catalogs));
    }
}