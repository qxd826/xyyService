package com.qxd.birth.web.app;

import com.qxd.birth.biz.SupplyService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;
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
@Controller
@Slf4j
@RequestMapping(value = "/app/supply")
public class SupplyController extends BaseController {

    @Autowired
    private SupplyService supplyService;

    /**
     * 获取供应商列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result getSupplyList() {
        List<Supply> supplyList = supplyService.getSupplyList();
        if (null == supplyList) {
            return Result.wrapErrorResult("", "获取供应商列表失败");
        } else {
            return Result.wrapSuccessfulResult(supplyList);
        }
    }

    /**
     * 添加供应商
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addSupply(@RequestBody Supply supply) {
        return supplyService.addSupply(supply);
    }

    /**
     * 搜索供应商
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Result searchGoods(@RequestParam("searchCon") String searchCon) {
        return supplyService.searchSupply(searchCon);
    }

    /**
     * 根据商品id获取供应商详情
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getGoodsInfoById(@RequestParam("supplyId") Long supplyId) {
        if (supplyId == null || supplyId < 1) {
            return Result.wrapErrorResult("", "供应商id错误 supplyId:{}" + supplyId);
        }
        return supplyService.supplyInfo(supplyId);
    }

    /**
     * 根据供应商id删除供应商
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Result delById(@RequestParam("supplyId") Long supplyId) {
        if (supplyId == null || supplyId < 1) {
            return Result.wrapErrorResult("", "供应商id错误 supplyId:{}" + supplyId);
        }
        return supplyService.delById(supplyId);
    }

    /**
     * 根据供应商编号获取商品入库明细
     *
     * @return
     */
    @RequestMapping(value = "/inOutDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result inOutDetail(@RequestParam("supplyId") Long supplyId) {
        if (supplyId == null || supplyId < 1) {
            return Result.wrapErrorResult("", "供应商id错误 supplyId:{}" + supplyId);
        }
        return supplyService.inOutDetail(supplyId);
    }

    /**
     * 根据供应商编号获取商品入库明细
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(@RequestBody Supply supply) {
        if (supply == null) {
            return Result.wrapErrorResult("", "供应商信息错误");
        }
        return supplyService.edit(supply);
    }

}
