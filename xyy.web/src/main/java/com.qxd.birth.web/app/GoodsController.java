package com.qxd.birth.web.app;

import com.qxd.birth.biz.GoodsService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Goods;
import com.qxd.birth.dal.entity.GoodsInOrOut;
import com.qxd.birth.dal.entity.Supply;
import com.qxd.birth.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
@Slf4j
@Controller
@RequestMapping(value = "/app/goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取商品列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result getGoodsList() {
        List<Goods> goodsList = goodsService.getGoodsList();
        if (null == goodsList) {
            return Result.wrapErrorResult("", "获取商品列表失败");
        } else {
            return Result.wrapSuccessfulResult(goodsList);
        }
    }

    /**
     * 添加商品
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    /**
     * 搜索商品
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Result searchGoods(@RequestParam("searchCon") String searchCon) {
        return goodsService.searchGoods(searchCon);
    }

    /**
     * 根据商品编号获取商品详情
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getGoodsInfoById(@RequestParam("goodsCode") String goodsCode) {
        if (StringUtils.isBlank(goodsCode)) {
            return Result.wrapErrorResult("", "商品编号错误 goodsCode:{}" + goodsCode);
        }
        return goodsService.goodsInfo(goodsCode);
    }

    /**
     * 根据商品id删除商品
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Result delById(@RequestParam("goodsId") Long goodsId) {
        if (goodsId == null || goodsId < 1 ) {
            return Result.wrapErrorResult("", "商品id错误 goodsId:{}" + goodsId);
        }
        return goodsService.delById(goodsId);
    }

    /**
     * 商品出入库
     *
     * @return
     */
    @RequestMapping(value = "/inOrOut", method = RequestMethod.POST)
    @ResponseBody
    public Result inOrOut(@RequestBody GoodsInOrOut goodsInOrOut) {
        if (goodsInOrOut == null ) {
            return Result.wrapErrorResult("", "参数错误");
        }
        if(goodsInOrOut.getGoodsNum() < 1){
            return Result.wrapErrorResult("", "出入库数量错误");
        }
        if(goodsInOrOut.getType() != 1 && goodsInOrOut.getType() != 0){
            return Result.wrapErrorResult("", "出入库类型错误");
        }
        if (goodsInOrOut.getType() == 0){
            if(goodsInOrOut.getSupplyId() < 1){
                return Result.wrapErrorResult("", "请选择供应商");
            }
        }
        if(goodsInOrOut.getType() == 1){
            if(goodsInOrOut.getCustomerId() < 1){
                return Result.wrapErrorResult("", "请选择客户");
            }
        }
        Result result;
        try{
            result = goodsService.inOrOut(goodsInOrOut);
        }catch (RuntimeException e){
            return Result.wrapErrorResult("","操作失败");
        }
        return result;
    }

    /**
     * 根据商品编号获取商品出入库明细
     *
     * @return
     */
    @RequestMapping(value = "/inOutDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result inOutDetail(@RequestParam("goodsCode") String goodsCode) {
        if (StringUtils.isBlank(goodsCode)) {
            return Result.wrapErrorResult("", "商品编号错误 goodsCode:{}" + goodsCode);
        }
        return goodsService.inOutDetail(goodsCode);
    }

}
