package org.example.controller;

import org.example.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:org.example.controller
 * @Date:2023/8/12
 * @Author:谢锦创
 */

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public Result check_health(){
        return Result.success("信息返回成功", "你好");
    }
}