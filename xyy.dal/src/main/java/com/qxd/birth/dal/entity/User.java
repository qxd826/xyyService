package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@EqualsAndHashCode (callSuper = false)
@Data
public class User extends BaseEntity implements Serializable{
    private String userName;//用户姓名
    private String account;//登录账户
    private String password;//登录密码
    private String isAdmin;//1是管理员,0是非管理员
    private String mobile;//用户电话
}


