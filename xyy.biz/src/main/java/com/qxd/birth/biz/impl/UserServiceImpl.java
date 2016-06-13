package com.qxd.birth.biz.impl;

import com.qxd.birth.biz.UserService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.dao.UserDao;
import com.qxd.birth.dal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据账号获取用户信息
     *
     * @param account
     *
     * @return
     */
    @Override
    public User getUserByAccount(String account) {
        Map<String, Object> param = new HashMap<>();
        param.put("account", account);
        List<User> userList = userDao.select(param);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    /**
     * 添加用户
     *
     * @param user
     *
     * @return
     */
    @Override
    public Result addUser(User user) {
        if (userDao.insert(user) < 1) {
            return Result.wrapErrorResult("", "添加用户失败");
        }
        return Result.wrapSuccessfulResult("添加成功");
    }

    /**
     * 判断是否有管理员
     *
     * @return
     */
    @Override
    public Result isHasAdmin() {
        Map<String, Object> param = new HashMap<>();
        param.put("isAdmin", "1");
        List<User> userList = userDao.select(param);
        if (CollectionUtils.isEmpty(userList)) {
            return Result.wrapSuccessfulResult(false);
        }
        return Result.wrapSuccessfulResult(true);
    }

    /**
     * 用户登录
     *
     * @param account
     * @param password
     *
     * @return
     */
    @Override
    public Result login(String account, String password) {
        Map<String, Object> param = new HashMap<>();
        param.put("account", account);
        List<User> userList = userDao.select(param);
        if (CollectionUtils.isEmpty(userList)) {
            return Result.wrapErrorResult("", "该账户不存在");
        } else if (userList.size() > 1) {
            return Result.wrapErrorResult("", "存在多个同名账户");
        }
        User user = userList.get(0);
        if(!StringUtils.endsWithIgnoreCase(password,user.getPassword())){
            return Result.wrapErrorResult("", "登录密码错误");
        }
        return Result.wrapSuccessfulResult(user);
    }
}
