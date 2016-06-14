package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Goods;
import com.qxd.birth.dal.entity.GoodsInOrOut;

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

    /**
     * 搜索商品
     *
     * @param searchCon
     *
     * @return
     */
    public Result searchGoods(String searchCon);

    /**
     * 根据商品编号获取商品详情
     *
     * @param goodsCode
     *
     * @return
     */
    public Result goodsInfo(String goodsCode);

    /**
     * 根据商品id删除商品
     *
     * @param goodsId
     *
     * @return
     */
    public Result delById(Long goodsId);

    /**
     * 商品出入库
     *
     * @param goodsInOrOut
     *
     * @return
     */
    public Result inOrOut( GoodsInOrOut goodsInOrOut);


    /**
     * 根据商品编号获取商品出入库明细
     * @param goodsCode
     * @return
     */
    public Result inOutDetail( String  goodsCode);
}
