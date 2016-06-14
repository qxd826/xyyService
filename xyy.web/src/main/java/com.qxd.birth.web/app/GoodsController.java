package com.qxd.birth.web.app;

import com.qxd.birth.biz.GoodsService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Goods;
import com.qxd.birth.dal.entity.Supply;
import com.qxd.birth.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping (value = "/list", method = RequestMethod.GET)
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
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }
}
