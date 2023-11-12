package org.example.util;

import org.example.entity.SysUser;

/**
 * @Description:com.ajie.utils
 * @Date:2023/11/9
 * @Author:谢锦创
 */
public class RequestContext {

    public static ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

//    private SysUser user;

    public static SysUser getCurrentUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }

}
