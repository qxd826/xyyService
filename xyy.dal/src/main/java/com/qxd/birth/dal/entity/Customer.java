package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@EqualsAndHashCode (callSuper = false)
@Data
public class Customer extends BaseEntity implements Serializable {
    private String customerName;//客户姓名
    private String customerMobile;//客户电话
}


