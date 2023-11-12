package org.example.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:org.example.handler
 * @Date:2023/11/12
 * @Author:谢锦创
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 没用权限时返回结果
     *
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("applicaiton/json");
        // TODO: 2023/11/12 写一个json返回  用Writer写入 权限不足，请联系管理员
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(Result.fail("权限不足，请联系管理员", response)));
        writer.flush();
        writer.close();
    }
}
