package org.example.mapper;

import org.example.entity.SysUser;

import java.util.List;

/**
 * @Description: 用户相关操作
 *
 * @Date:2023/11/8
 * @Author:谢锦创
 */
public interface SysUserMapper {

    List<SysUser> findAll();

    SysUser findByUserName(String userName);
}
