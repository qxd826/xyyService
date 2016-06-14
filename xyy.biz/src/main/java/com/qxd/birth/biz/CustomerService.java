package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;

import java.util.List;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
public interface CustomerService {

    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Customer> getCustomerList();


    /**
     * 添加客户
     *
     * @param customer
     *
     * @return
     */
    public Result addCustomer(Customer customer);
}
