package org.example.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.SysUser;
import org.example.mapper.SysUserMapper;
import org.example.service.SysUserService;
import org.example.util.Result;
import org.example.util.TokenUtil;
import org.example.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:org.example.impl
 * @Date:2023/11/8
 * @Author:谢锦创
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public Result findAll() {
        log.info("获取用户信息");
        return Result.success("用户用户信息成功", userMapper.findAll());
    }

    @Override
    public Result login(LoginVo loginVo) {
        log.info("开始登录");
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
        if (Objects.isNull(userDetails) ) {
//            !passwordEncoder.matches(loginVo.getPassword(), userDetails.getPassword()
            return Result.fail("账号或密码错误，请重新输入!");
        }
        if (!userDetails.isEnabled()) {
            return Result.fail("该账号已禁用，请联系管理员");
        }
        // 在security对象中设置登陆者信息
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 借助jwt生成token
        String token = tokenUtil.generateToken(userDetails);
        Map<String, String> map = new HashMap(2);
        map.put("tokenHead", tokenHead);
        map.put("token", token);
        return Result.success("登录成功", map);
    }

    @Override
    public SysUser findByUsername(String username) {
        return userMapper.findByUserName(username);
    }
}
