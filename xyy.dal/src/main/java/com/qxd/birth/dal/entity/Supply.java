package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@EqualsAndHashCode (callSuper = false)
@Data
public class Supply extends BaseEntity implements Serializable {
    private String supplyName;//供应商名称
    private String supplyMobile;//供应商电话
}


