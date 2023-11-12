package org.example.filter;

import org.example.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token认证，在接口访问前进行过滤
 *
 * @Description:org.example.filter
 * @Date:2023/11/12
 * @Author:谢锦创
 */
@Component // 为什么要被spring管理
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    /**
     * 请求前获取请求头信息token
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String header = request.getHeader(tokenHeader);
        // 判断token是否存在
        if (Objects.nonNull(header) && header.startsWith(tokenHeader)) {
            // 拿到token主体
            String token = header.substring(header.length());
            // 根据token拿到用户名
            String userName = tokenUtil.getUserNameByToken(token);
            // token存在，但是没有登陆信息
            if (Objects.nonNull(token) && null == SecurityContextHolder.getContext().getAuthentication()) {
                // 没有登录信息，直接登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                // 判断token是否有效
                if (!tokenUtil.isExpiration(token) && userDetails.equals(userDetails.getUsername())) {
                    // 刷新security中的用户信息
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails();
                }
            }
        }
    }
}
