package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by QXD on 2016/6/14.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class GoodsInOrOut {
    private String goodsCode;
    private Long goodsNum;
    private int type;  //入库0出库1
    private Long supplyId;//供应商id
    private Long customerId;//客户id
}

