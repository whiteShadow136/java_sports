package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description:org.example
 * @Date:2023/8/12
 * @Author:谢锦创
 */

@SpringBootApplication
@MapperScan("org.example.mapper")
@ServletComponentScan
public class JavaSportApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JavaSportApplication.class);
        System.out.println(11111);
    }
}
