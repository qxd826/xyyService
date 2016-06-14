package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Goods;

import java.util.List;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
public interface GoodsService {
    /**
     * 获取商品列表
     *
     * @return
     */
    public List<Goods> getGoodsList();

    /**
     * 添加商品
     *
     * @param goods
     *
     * @return
     */
    public Result addGoods(Goods goods);
}
