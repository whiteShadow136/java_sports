package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:org.example.util
 * @Date:2023/11/11
 * @Author:谢锦创
 */
@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UserDetails userDetails) {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("userName", userDetails.getUsername());
        map.put("password", userDetails.getPassword());
        map.put("createTime", new Date());
        return generateJwt(map);
    }

    private String generateJwt(Map<String, Object> map) {
        return  Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 根据token字符串获取token实体
     *
     * @param token token字符串
     * @return token载荷
     */
    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据token获取用户名
     *
     * @param token token字符串
     * @return 用户名
     */
    public String getUserNameByToken(String token) {
        return this.getTokenBody(token).getSubject();
    }

    /**
     * 判断token是否过期
     *
     * @param token token字符串
     * @return 是否过期
     */
    public boolean isExpiration(String token) {
        return this.getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 根据当前token刷新token(其实就是生成新的token，设置一个新的生成时间)
     *
     * @return 新token
     */
    public String refreshToken(String token) {
        Claims claims = this.getTokenBody(token);
        claims.setExpiration(new Date());
        return this.generateJwt(claims);
    }
}
