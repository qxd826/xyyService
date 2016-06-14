package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.Customer;
import com.qxd.birth.dal.entity.Supply;

import java.util.List;

/**
 * Created by xiangDong.qu on 16/6/14.
 */
public interface SupplyService {
    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Supply> getSupplyList();


    /**
     * 添加客户
     *
     * @param supply
     *
     * @return
     */
    public Result addSupply(Supply supply);
}
