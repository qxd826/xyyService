package com.qxd.birth.web.app;

import com.qxd.birth.biz.UserService;
import com.qxd.birth.common.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiangDong.qu on 15/10/26.
 */
@RequestMapping ("/app/login")
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping ("/loginIn")
    @ResponseBody
    public Result login(@RequestParam ("account") String account, @RequestParam ("password") String password) {
        return userService.login(account, password);
    }
}
