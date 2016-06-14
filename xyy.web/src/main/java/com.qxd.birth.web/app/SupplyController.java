package com.qxd.birth.web.app;

import com.qxd.birth.biz.SupplyService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;
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
@Controller
@Slf4j
@RequestMapping (value = "/app/supply")
public class SupplyController extends BaseController {

    @Autowired
    private SupplyService supplyService;

    /**
     * 获取供应商列表
     *
     * @return
     */
    @RequestMapping (value = "/list", method = RequestMethod.GET)
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
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addSupply(@RequestBody Supply supply) {
        return supplyService.addSupply(supply);
    }
}
