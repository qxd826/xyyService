package com.qxd.birth.web.app;

import com.qxd.birth.biz.UserService;
import com.qxd.birth.common.common.Result;
import com.qxd.birth.dal.entity.User;
import com.qxd.birth.web.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiangDong.qu on 16/6/13.
 */
@RequestMapping (value = "/app/user")
@Controller
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户账号获取用户信息
     *
     * @param account
     *
     * @return
     */
    @RequestMapping (value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserInfo(@RequestParam ("account") String account) {
        if (StringUtils.isBlank(account)) {
            return Result.wrapErrorResult("", "参数错误");
        }
        User user = userService.getUserByAccount(account);
        if (user == null) {
            return Result.wrapErrorResult("", "该用户不存在");
        }
        return Result.wrapSuccessfulResult(user);
    }

    /**
     * 添加用户
     *
     * @param user
     *
     * @return
     */
    @RequestMapping (value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(@RequestBody User user) {
        if (user == null) {
            return Result.wrapErrorResult("", "参数错误");
        }
        return userService.addUser(user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     *
     * @return
     */
    @RequestMapping (value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result editUser(@RequestBody User user) {
        if (user == null) {
            return Result.wrapErrorResult("", "参数错误");
        }
        return userService.editUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param user
     *
     * @return
     */
    @RequestMapping (value = "/editPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result editPassword(@RequestBody User user) {
        if (user == null) {
            return Result.wrapErrorResult("", "参数错误");
        }
        return userService.editPassword(user);
    }

    /**
     * 判断是否有管理员
     *
     * @return
     */
    @RequestMapping (value = "/isHasAdmin", method = RequestMethod.GET)
    @ResponseBody
    public Result isHasAdmin() {
        return userService.isHasAdmin();
    }

    /**
     * 判断是否有管理员
     *
     * @return
     */
    @RequestMapping (value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserList() {
        List<User> userList = userService.getUserList();
        if(null == userList){
            return  Result.wrapErrorResult("","获取用户列表失败");
        }
        return Result.wrapSuccessfulResult(userList);
    }
}
