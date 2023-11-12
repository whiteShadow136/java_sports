package org.example.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

/**
 * @Description:org.example.entity
 * @Date:2023/11/8
 * @Author:谢锦创
 */
@Data
@Builder
public class SysUser implements UserDetails {
//    @ApiModelProperty(value = "主键")
    private Long id;
//    @ApiModelProperty(value = "用户名")
    private String userName;

//    @ApiModelProperty(value = "前端展示的用户名")
//    private String name;

//    @ApiModelProperty(value = "用户昵称")
    private String nickName;

//    @ApiModelProperty(value = "登录密码")
    private String password;
//    @ApiModelProperty(value = "性别")
    private Integer sex;
//    @ApiModelProperty(value = "头像")
    private String avatar;
//    @ApiModelProperty(value = "地址")
    private String address;
//    @ApiModelProperty(value = "微信唯一ID")
    private String openId;
//    @ApiModelProperty(value = "当前状态")
    private boolean status;
//    @ApiModelProperty(value = "是否是管理员")
    private boolean admin;
//    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

//    @ApiModelProperty(value = "用户邮箱")
    private String email;

//    @ApiModelProperty(value = "角色信息")
    private List<SysRole> roles;
//
//    @ApiModelProperty(value = "用户对应的菜单列表")
    private List<SysMenu> menus;

//    @ApiModelProperty(value = "用户对应的权限数据")
//    private List<SysPermission> permissions;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
