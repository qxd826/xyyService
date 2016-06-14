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

    /**
     * 搜索供应商
     *
     * @param searchCon
     *
     * @return
     */
    public Result searchSupply(String searchCon);

    /**
     * 根据供应商id获取供应商详情
     *
     * @param supplyId
     *
     * @return
     */
    public Result supplyInfo(Long supplyId);

    /**
     * 根据供应商id删除供应商
     *
     * @param supplyId
     *
     * @return
     */
    public Result delById(Long supplyId);

    /**
     * 根据供应商编号获取商品出入库明细
     * @param supplyId
     * @return
     */
    public Result inOutDetail( Long  supplyId);

    /**
     * 供应商信息编辑
     * @param supply
     * @return
     */
    public Result edit( Supply  supply);
}
