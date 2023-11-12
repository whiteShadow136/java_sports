package org.example.controller;

import org.example.service.SysUserService;
import org.example.util.Result;
import org.example.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * 登录
 * 退出
 * 获取登录用户的基本信息
 * 相关的接口
 * @Date:2023/11/8
 * @Author:谢锦创
 */
@RestController
public class LoginController {

    @Autowired
    SysUserService userService;


    @PostMapping("/user/login")
    public Result login(@RequestBody LoginVo loginVo) {
        Result result = userService.login(loginVo);
        return result;
    }
}
