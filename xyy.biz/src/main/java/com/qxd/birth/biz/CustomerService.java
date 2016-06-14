package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;
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
    /**
     * 搜索客户
     *
     * @param searchCon
     *
     * @return
     */
    public Result searchCustomer(String searchCon);

    /**
     * 根据客户id获取客户详情
     *
     * @param CustomerId
     *
     * @return
     */
    public Result CustomerInfo(Long CustomerId);

    /**
     * 根据客户id删除客户
     *
     * @param CustomerId
     *
     * @return
     */
    public Result delById(Long CustomerId);

    /**
     * 根据客户编号获取商品出入库明细
     * @param CustomerId
     * @return
     */
    public Result inOutDetail(Long CustomerId);

    /**
     * 客户信息编辑
     * @param Customer
     * @return
     */
    public Result edit(Customer Customer);
}
