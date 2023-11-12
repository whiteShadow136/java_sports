package org.example.service;

import com.mysql.cj.log.Log;
import org.example.entity.SysUser;
import org.example.util.Result;
import org.example.vo.LoginVo;

/**
 * @Description:org.example.service
 * @Date:2023/11/8
 * @Author:谢锦创
 */
public interface SysUserService {

    Result findAll();

    /**
     * 登录接口
     *
     * @param loginVo 登录参数: 账号密码
     * @return token，可以用token去获取资源
     */
    Result login(LoginVo loginVo);

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return SysUser对象
     */
    SysUser findByUsername(String  username);
}
