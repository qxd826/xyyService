package com.qxd.birth.web.app;

import com.qxd.birth.biz.CustomerService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;
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
}
