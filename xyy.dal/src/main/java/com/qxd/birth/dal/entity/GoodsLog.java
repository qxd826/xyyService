package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@EqualsAndHashCode (callSuper = false)
@Data
public class GoodsLog extends BaseEntity implements Serializable {
    private String goodsName;//商品名称
    private String goodsCode;//商品编码
    private Long goodsNum;//商品数量
    private Long customerId;//客户id
    private String customerName;//客户姓名
    private String supplyName;//供应商名称
    private Long supplyId;//供应商id
    private String actionType;//0:入库1:出库
}


