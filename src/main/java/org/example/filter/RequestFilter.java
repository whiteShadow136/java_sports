package org.example.filter;

import org.apache.catalina.User;
import org.example.entity.SysUser;
import org.example.util.RequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.LogRecord;

/**
 * @Description:org.example.filter
 * @Date:2023/11/9
 * @Author:谢锦创
 */
@WebFilter("/*")
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object parameter = request.getParameter("user");
        if (Objects.nonNull(parameter)) {
            String userName = (String)parameter;
            SysUser user = SysUser.builder().userName(userName).build();
            RequestContext.threadLocal.set(user);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        RequestContext.clear();
    }
}
