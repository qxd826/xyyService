package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.CustomerService;
import com.qxd.birth.biz.GoodsService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.CustomerDao;
import com.qxd.birth.dal.dao.GoodsDao;
import com.qxd.birth.dal.dao.GoodsLogDao;
import com.qxd.birth.dal.dao.SupplyDao;
import com.qxd.birth.dal.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SupplyDao supplyDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private GoodsLogDao goodsLogDao;

    /**
     * 获取商品列表
     *
     * @return
     */
    @Override
    public List<Goods> getGoodsList() {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        List<Goods> goodses = goodsDao.select(param);
        return goodses;
    }

    /**
     * 添加商品
     *
     * @param goods
     *
     * @return
     */
    @Override
    public Result addGoods(Goods goods) {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("goodsCode", goods.getGoodsCode());
        List<Goods> goodsList = goodsDao.select(param);
        if (!CollectionUtils.isEmpty(goodsList)) {
            return Result.wrapErrorResult("", "当前商品已存在");
        }
        if (goodsDao.insert(goods) > 0) {
            return Result.wrapSuccessfulResult("添加成功");
        }
        return Result.wrapErrorResult("", "添加失败");
    }

    /**
     * 搜索商品
     *
     * @param searchCon
     *
     * @return
     */
    @Override
    public Result searchGoods(String searchCon) {
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("searchCon", searchCon);
        List<Goods> goodsList = goodsDao.select(param);
        if(goodsList == null){
            return Result.wrapErrorResult("", "搜索失败");
        }
        return Result.wrapSuccessfulResult(goodsList);
    }

    /**
     * 根据商品编号获取商品详情
     *
     * @param goodsCode
     *
     * @return
     */
    @Override
    public Result goodsInfo(String goodsCode) {
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("goodsCode", goodsCode);
        List<Goods> goodsList = goodsDao.select(param);
        if(CollectionUtils.isEmpty(goodsList)){
            return Result.wrapErrorResult("", "获取失败");
        }
        return Result.wrapSuccessfulResult(goodsList.get(0));
    }

    /**
     * 根据商品id删除商品
     *
     * @param goodsId
     *
     * @return
     */
    @Override
    public Result delById(Long goodsId) {
        if(goodsDao.deleteById(goodsId) < 1){
            return Result.wrapErrorResult("", "删除失败");
        }
        return Result.wrapSuccessfulResult("删除成功");
    }

    /**
     * 商品出入库
     *
     * @param goodsInOrOut
     *
     * @return
     */
    @Override
    @Transactional
    public Result inOrOut(GoodsInOrOut goodsInOrOut) {
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("goodsCode", goodsInOrOut.getGoodsCode());
        List<Goods> goodsList = goodsDao.select(param);
        if(CollectionUtils.isEmpty(goodsList)){
            return Result.wrapErrorResult("", "获取商品信息失败");
        }
        Goods goods = goodsList.get(0);

        Goods goodsTemp = new Goods();
        goodsTemp.setId(goods.getId());

        GoodsLog goodsLogTemp = new GoodsLog();
        goodsLogTemp.setGoodsNum(goods.getGoodsNum());
        goodsLogTemp.setGoodsNum(goodsInOrOut.getGoodsNum());
        goodsLogTemp.setGoodsCode(goods.getGoodsCode());

        if(goodsInOrOut.getType() == 0){
            Supply supply = supplyDao.selectById(goodsInOrOut.getSupplyId());
            if(null == supply){
                return Result.wrapErrorResult("", "获取供应商信息错误");
            }

            goodsTemp.setGoodsNum(goods.getGoodsNum() + goodsInOrOut.getGoodsNum());
            goodsLogTemp.setActionType("0");
            goodsLogTemp.setSupplyId(supply.getId());
            goodsLogTemp.setSupplyName(supply.getSupplyName());
        }
        if(goodsInOrOut.getType() == 1){
            Customer customer = customerDao.selectById(goodsInOrOut.getSupplyId());
            if(null == customer){
                return Result.wrapErrorResult("", "获取客户信息错误");
            }
            if(goods.getGoodsNum() < goodsInOrOut.getGoodsNum()){
                return Result.wrapErrorResult("", "商品数量不足");
            }

            goodsTemp.setGoodsNum(goods.getGoodsNum() - goodsInOrOut.getGoodsNum());
            goodsLogTemp.setActionType("1");
            goodsLogTemp.setCustomerId(customer.getId());
            goodsLogTemp.setCustomerName(customer.getCustomerName());
        }

        if(goodsDao.updateById(goodsTemp) > 0){
            if(goodsLogDao.insert(goodsLogTemp) < 1){
                throw new RuntimeException("");
            }
        }else{
            throw new RuntimeException("");
        }
        return Result.wrapSuccessfulResult("操作成功");
    }

    /**
     * 根据商品编号获取商品出入库明细
     * @param goodsCode
     * @return
     */
    @Override
    public Result inOutDetail(String goodsCode) {
        Map<String,Object> param = new HashMap<>();
        param.put("goodsCode",goodsCode);
        param.put("isDeleted","N");
        param.put("sorts",new String[]{"id desc"});
        List<GoodsLog> goodsLogs = goodsLogDao.select(param);
        if(null == goodsLogs){
            return Result.wrapErrorResult("","获取商品出入库明细失败");
        }
        return Result.wrapSuccessfulResult(goodsLogs);
    }
}
