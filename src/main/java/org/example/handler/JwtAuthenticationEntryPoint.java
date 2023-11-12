package org.example.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当用户未登录和token过期的情况下访问资源
 *
 * @Description:org.example.handler
 * @Date:2023/11/12
 * @Author:谢锦创
 */
@Component // 这里为什么需要spring托管
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private PrintWriter writer;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(400);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("applicaiton/json");
        // TODO: 2023/11/12 写一个json返回  用Writer写入 用户信息已过期
        PrintWriter writer  = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(Result.fail("用户信息已过期", response)));
        writer.flush();
        writer.close();
    }
}
