package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@EqualsAndHashCode (callSuper = false)
@Data
public class Goods extends BaseEntity implements Serializable {
    private String goodsName;//商品名称
    private String goodsCode;//商品编码
    private Long goodsNum;//商品数量
}


