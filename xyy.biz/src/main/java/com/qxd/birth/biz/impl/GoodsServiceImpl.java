package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.GoodsService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.GoodsDao;
import com.qxd.birth.dal.entity.Goods;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

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
}
