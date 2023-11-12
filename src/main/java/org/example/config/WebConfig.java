package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:org.example.config
 * @Date:2023/8/19
 * @Author:谢锦创
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许请求的路径
                .addMapping("/**")
                //允许请求的来源
                .allowedOrigins("http://localhost:8080")
                //允许请求的方法
                .allowedMethods("GET", "POST")
                //是否允许携带参数
                .allowCredentials(true);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
