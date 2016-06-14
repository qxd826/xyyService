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
        Map<String, Object> param = new HashMap<>();
        param.put("account", user.getAccount());
        List<User> userList = userDao.select(param);
        if(!CollectionUtils.isEmpty(userList)){
            return Result.wrapErrorResult("", "该账户已存在");
        }
        if (userDao.insert(user) < 1) {
            return Result.wrapErrorResult("", "添加用户失败");
        }
        return Result.wrapSuccessfulResult("添加成功");
    }

    /**
     * 修改用户
     *
     * @param user
     *
     * @return
     */
    @Override
    public Result editUser(User user) {
        if(userDao.updateById(user) > 0){
            return Result.wrapSuccessfulResult("更新成功");
        }
        return Result.wrapErrorResult("", "更新失败");
    }

    @Override
    public Result editPassword(User user) {
        User userTemp = userDao.selectById(user.getId());
        if(userTemp == null){
            return Result.wrapSuccessfulResult("当前用户不存在");
        }
        if(!StringUtils.equalsIgnoreCase(user.getPassword(),userTemp.getPassword())){
            return Result.wrapSuccessfulResult("旧密码错误");
        }else{
            User updateUser = new User();
            updateUser.setId(userTemp.getId());
            updateUser.setPassword(user.getNewPassword());
            if(userDao.updateById(updateUser) > 0){
                return Result.wrapSuccessfulResult("更新成功");
            }
            return Result.wrapErrorResult("", "更新失败");
        }
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

    @Override
    public List<User> getUserList() {
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", "N");
        List<User> userList = userDao.select(param);
        return userList;
    }

    @Override
    public Result delUser(Long userId) {
        User user = userDao.selectById(userId);
        if(StringUtils.equalsIgnoreCase("1",user.getIsAdmin())){
            return Result.wrapErrorResult("","管理员不能删除");
        }
        if(userDao.deleteById(userId) > 0){
            return Result.wrapSuccessfulResult("删除成功");
        }else{
            return Result.wrapErrorResult("","删除失败");
        }
    }
}
