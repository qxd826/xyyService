package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.User;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
public interface UserService {

    /**
     * 根据账号获取用户信息
     *
     * @param account
     *
     * @return
     */
    public User getUserByAccount(String account);

    /**
     * 添加用户
     *
     * @param user
     *
     * @return
     */
    public Result addUser(User user);

    /**
     * 判断是否有管理员
     *
     * @return
     */
    public Result isHasAdmin();
}
