package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.SupplyService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.GoodsLogDao;
import com.qxd.birth.dal.dao.SupplyDao;
import com.qxd.birth.dal.entity.Customer;
import com.qxd.birth.dal.entity.Goods;
import com.qxd.birth.dal.entity.GoodsLog;
import com.qxd.birth.dal.entity.Supply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
@Service
@Slf4j
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyDao supplyDao;

    @Autowired
    private GoodsLogDao goodsLogDao;

    /**
     * 获取客户列表
     *
     * @return
     */
    @Override
    public List<Supply> getSupplyList() {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        List<Supply> supplies = supplyDao.select(param);
        return supplies;
    }

    /**
     * 添加客户
     *
     * @param supply
     *
     * @return
     */
    @Override
    public Result addSupply(Supply supply) {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("supplyName", supply.getSupplyName());
        List<Supply> supplies = supplyDao.select(param);
        if (!CollectionUtils.isEmpty(supplies)) {
            return Result.wrapErrorResult("", "当前供应商已存在");
        }
        if (supplyDao.insert(supply) > 0) {
            return Result.wrapSuccessfulResult("添加成功");
        }
        return Result.wrapErrorResult("", "添加失败");
    }

    /**
     * 搜索供应商
     *
     * @param searchCon
     *
     * @return
     */
    @Override
    public Result searchSupply(String searchCon) {
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("searchCon", searchCon);
        List<Supply> supplyList = supplyDao.select(param);
        if(supplyList == null){
            return Result.wrapErrorResult("", "搜索失败");
        }
        return Result.wrapSuccessfulResult(supplyList);
    }

    /**
     * 根据供应商id获取供应商详情
     *
     * @param supplyId
     *
     * @return
     */
    @Override
    public Result supplyInfo(Long supplyId) {
        Supply supply = supplyDao.selectById(supplyId);
        if(supply == null){
            return Result.wrapErrorResult("","该供应商不存在");
        }
        return Result.wrapSuccessfulResult(supply);
    }

    /**
     * 根据供应商id删除供应商
     *
     * @param supplyId
     *
     * @return
     */
    @Override
    public Result delById(Long supplyId) {
        if(supplyDao.deleteById(supplyId) < 1){
            return Result.wrapErrorResult("", "删除失败");
        }
        return Result.wrapSuccessfulResult("删除成功");
    }

    /**
     * 根据供应商id获取商品出入库明细
     * @param supplyId
     * @return
     */
    @Override
    public Result inOutDetail(Long supplyId) {
        Map<String,Object> param = new HashMap<>();
        param.put("supplyId",supplyId);
        param.put("isDeleted","N");
        param.put("action_type",1);
        param.put("sorts",new String[]{"id desc"});
        List<GoodsLog> goodsLogs = goodsLogDao.select(param);
        if(null == goodsLogs){
            return Result.wrapErrorResult("","获取商品出入库明细失败");
        }
        return Result.wrapSuccessfulResult(goodsLogs);
    }

    /**
     * 供应商信息编辑
     * @param supply
     * @return
     */
    @Override
    public Result edit(Supply supply) {
       if(supplyDao.updateById(supply) > 0){
           return  Result.wrapSuccessfulResult("修改成功");
       }
        return  Result.wrapSuccessfulResult("修改失败");
    }
}
