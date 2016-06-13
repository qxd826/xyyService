package com.qxd.birth.biz;

import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.User;

import java.util.List;

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
     * 修改用户
     *
     * @param user
     *
     * @return
     */
    public Result editUser(User user);

    /**
     * 修改用户
     *
     * @param user
     *
     * @return
     */
    public Result editPassword(User user);

    /**
     * 判断是否有管理员
     *
     * @return
     */
    public Result isHasAdmin();


    /**
     * 用户登录
     *
     * @param account
     * @param password
     *
     * @return
     */
    public Result login(String account, String password);

    /**
     * 获取用户列表
     *
     * @return
     */
    public List<User> getUserList();
}
