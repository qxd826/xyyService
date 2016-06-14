package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.CustomerService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.CustomerDao;
import com.qxd.birth.dal.entity.Customer;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 获取客户列表
     *
     * @return
     */
    @Override
    public List<Customer> getCustomerList() {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        List<Customer> customers = customerDao.select(param);
        return customers;
    }

    /**
     * 添加客户
     *
     * @param customer
     *
     * @return
     */
    @Override
    public Result addCustomer(Customer customer) {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("customerName", customer.getCustomerName());
        List<Customer> customers = customerDao.select(param);
        if (!CollectionUtils.isEmpty(customers)) {
            return Result.wrapErrorResult("", "当前客户已存在");
        }
        if (customerDao.insert(customer) > 0) {
            return Result.wrapSuccessfulResult("添加成功");
        }
        return Result.wrapErrorResult("", "添加失败");
    }
}
