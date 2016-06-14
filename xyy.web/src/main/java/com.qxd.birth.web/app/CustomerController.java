package com.qxd.birth.web.app;

import com.qxd.birth.biz.CustomerService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;
import com.qxd.birth.dal.entity.Customer;
import com.qxd.birth.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
@Controller
@Slf4j
@RequestMapping (value = "/app/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    /**
     * 获取客户列表
     *
     * @return
     */
    @RequestMapping (value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result getCustomerList() {
        List<Customer> customerList = customerService.getCustomerList();
        if (null == customerList) {
            return Result.wrapErrorResult("", "获取客户列表失败");
        } else {
            return Result.wrapSuccessfulResult(customerList);
        }
    }

    /**
     * 添加客户
     *
     * @return
     */
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    /**
     * 搜索客户
     *
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Result searchGoods(@RequestParam("searchCon") String searchCon) {
        return customerService.searchCustomer(searchCon);
    }

    /**
     * 根据商品编号获取商品详情
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getGoodsInfoById(@RequestParam("customerId") Long customerId) {
        if (customerId == null || customerId < 1) {
            return Result.wrapErrorResult("", "客户id错误 CustomerId:{}" + customerId);
        }
        return customerService.CustomerInfo(customerId);
    }

    /**
     * 根据客户id删除客户
     *
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Result delById(@RequestParam("customerId") Long customerId) {
        if (customerId == null || customerId < 1) {
            return Result.wrapErrorResult("", "客户id错误 CustomerId:{}" + customerId);
        }
        return customerService.delById(customerId);
    }

    /**
     * 根据客户编号获取商品入库明细
     *
     * @return
     */
    @RequestMapping(value = "/inOutDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result inOutDetail(@RequestParam("customerId") Long customerId) {
        if (customerId == null || customerId < 1) {
            return Result.wrapErrorResult("", "客户id错误 customerId:{}" + customerId);
        }
        return customerService.inOutDetail(customerId);
    }

    /**
     * 根据客户编号获取商品入库明细
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result edit(@RequestBody Customer customer) {
        if (customer == null) {
            return Result.wrapErrorResult("", "客户信息错误");
        }
        return customerService.edit(customer);
    }
}
