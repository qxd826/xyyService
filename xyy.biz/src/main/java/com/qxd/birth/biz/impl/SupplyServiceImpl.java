package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.SupplyService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.SupplyDao;
import com.qxd.birth.dal.entity.Customer;
import com.qxd.birth.dal.entity.Supply;
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
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyDao supplyDao;

    /**
     * 获取客户列表
     *
     * @return
     */
    @Override
    public List<Supply> getSupplyList() {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        List<Supply> supplies = supplyDao.select(param);
        return supplies;
    }

    /**
     * 添加客户
     *
     * @param supply
     *
     * @return
     */
    @Override
    public Result addSupply(Supply supply) {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        param.put("supplyName", supply.getSupplyName());
        List<Supply> supplies = supplyDao.select(param);
        if (!CollectionUtils.isEmpty(supplies)) {
            return Result.wrapErrorResult("", "当前供应商已存在");
        }
        if (supplyDao.insert(supply) > 0) {
            return Result.wrapSuccessfulResult("添加成功");
        }
        return Result.wrapErrorResult("", "添加失败");
    }
}
