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
import com.hjy.sjzyml.dao.DDataRecordMapper;
import com.hjy.sjzyml.dao.TDataMapper;
import com.hjy.sjzyml.entity.DDataRecord;
import com.hjy.sjzyml.entity.TCatalog;
import com.hjy.sjzyml.entity.TData;
import com.hjy.sjzyml.service.TDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TData)表服务实现类
 *
 * @author makejava
 * @since 2020-12-02 15:01:03
 */
@Service("tDataService")
public class TDataServiceImpl implements TDataService {

    @Resource
    private TDataMapper tDataMapper;
    @Resource
    private DDataRecordMapper dataRecordMapper;
    /**
     * 添加前获取数据
     *
     * @return
     */
    @Override
    public CommonResult insertPage() {
        return new CommonResult(200,"success","数据获取成功！",null);

    }

    /**
     * 添加数据
     *
     * @param tData
     * @return
     */
    @Override
    public CommonResult insert(TData tData) {
        String pkDataId = IDUtils.getUUID();
        tData.setPkDataId(pkDataId);
        tData.setSjcjsj(new Date());
        //添加数据项记录时间
        DDataRecord d_data_record = new DDataRecord();
        d_data_record.setPkDatarecordId(IDUtils.getUUID());
        d_data_record.setFkDataId(pkDataId);
        d_data_record.setUpdateTime(new Date());
        d_data_record.setUpdatePeople(tData.getSjly());
        int j = dataRecordMapper.insertSelective(d_data_record);
        //添加数据
        int i = tDataMapper.insertSelective(tData);
        if (i > 0) {
            return new CommonResult(200,"success","数据项添加成功！",tData);
        } else {
            return new CommonResult(444,"error","数据项添加失败！",null);
        }
    }

    /**
     * 修改数据
     *
     * @param tData
     * @return
     */
    @Override
    public CommonResult update(TData tData) {
        //修改主数据
        int i = tDataMapper.update(tData);
        //记录该数据修改时间线
        DDataRecord dDataRecord = new DDataRecord();
        dDataRecord.setPkDatarecordId(IDUtils.getUUID());
        dDataRecord.setFkDataId(tData.getPkDataId());
        dDataRecord.setUpdateTime(new Date());
        dDataRecord.setUpdatePeople("管理员");
        int j = dataRecordMapper.insertSelective(dDataRecord);
        if (i > 0) {
            return new CommonResult(200,"success","数据修改成功",tData);
        } else {
            return new CommonResult(444,"error","数据修改失败",null);
        }
    }

    /**
     * 删除数据
     *
     * @param param
     * @return
     */
    @Override
    public CommonResult delete(String param) {
        JSONObject json = JSON.parseObject(param);
        String pk_id = JsonUtil.getStringParam(json,"pk_id");
        int i = tDataMapper.deleteById(pk_id);
        if (i > 0) {
            return new CommonResult(200,"success","数据删除成功!",null);
        } else {
            return new CommonResult(444,"error","数据删除失败!",null);
        }
    }

    /**
     * 查询所有数据
     *
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
        TData tData = tDataMapper.selectByPkId(pk_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("entity",tData);
        //修改时间线数据
        List<DDataRecord> updateRecords = dataRecordMapper.selectRecordByDataId(pk_id);
        jsonObject.put("updateRecords",updateRecords);
        return new CommonResult(200,"success","查询数据成功!",jsonObject);
    }
    public PageResult selectAllPage(String param) {
        JSONObject json = JSON.parseObject(param);
        //实体数据
        String pageNumStr = JsonUtil.getStringParam(json, "pageNum");
        String pageSizeStr = JsonUtil.getStringParam(json, "pageSize");
//        String mlmc = JsonUtil.getStringParam(json, "mlmc");
//        String mllx = JsonUtil.getStringParam(json, "mllx");
//        String deptId = JsonUtil.getStringParam(json, "deptId");
        TData entity = new TData();
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
        List<TData> dataList = tDataMapper.selectAllPage(entity);
        return PageUtils.getPageResult(new PageInfo<TData>(dataList));
    }
}