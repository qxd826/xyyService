package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.CustomerService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.CustomerDao;
import com.qxd.birth.dal.dao.GoodsLogDao;
import com.qxd.birth.dal.entity.Customer;
import com.qxd.birth.dal.entity.GoodsLog;
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

    @Autowired
    private GoodsLogDao goodsLogDao;

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

    /**
     * 搜索客户
     *
     * @param searchCon
     *
     * @return
     */
    @Override
    public Result searchCustomer(String searchCon) {
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("searchCon", searchCon);
        List<Customer> CustomerList = customerDao.select(param);
        if(CustomerList == null){
            return Result.wrapErrorResult("", "搜索失败");
        }
        return Result.wrapSuccessfulResult(CustomerList);
    }

    /**
     * 根据客户id获取客户详情
     *
     * @param CustomerId
     *
     * @return
     */
    @Override
    public Result CustomerInfo(Long CustomerId) {
        Customer Customer = customerDao.selectById(CustomerId);
        if(Customer == null){
            return Result.wrapErrorResult("","该客户不存在");
        }
        return Result.wrapSuccessfulResult(Customer);
    }

    /**
     * 根据客户id删除客户
     *
     * @param CustomerId
     *
     * @return
     */
    @Override
    public Result delById(Long CustomerId) {
        if(customerDao.deleteById(CustomerId) < 1){
            return Result.wrapErrorResult("", "删除失败");
        }
        return Result.wrapSuccessfulResult("删除成功");
    }

    /**
     * 根据客户id获取商品出入库明细
     * @param CustomerId
     * @return
     */
    @Override
    public Result inOutDetail(Long CustomerId) {
        Map<String,Object> param = new HashMap<>();
        param.put("CustomerId",CustomerId);
        param.put("isDeleted","N");
        param.put("actionType",0);
        param.put("sorts",new String[]{"id desc"});
        List<GoodsLog> goodsLogs = goodsLogDao.select(param);
        if(null == goodsLogs){
            return Result.wrapErrorResult("","获取商品出入库明细失败");
        }
        return Result.wrapSuccessfulResult(goodsLogs);
    }

    /**
     * 客户信息编辑
     * @param Customer
     * @return
     */
    @Override
    public Result edit(Customer Customer) {
        if(customerDao.updateById(Customer) > 0){
            return  Result.wrapSuccessfulResult("修改成功");
        }
        return  Result.wrapSuccessfulResult("修改失败");
    }
}
