package org.example.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description:org.example.configuration
 * @Date:2023/11/8
 * @Author:谢锦创
 */
@Configuration
public class MyConfiguration {

    @Bean
    public DataSource myDataSource() throws SQLException {
        DruidDataSource driverDataSource = new DruidDataSource();
        driverDataSource.setUrl("jdbc:mysql://139.159.189.207:3307/sport?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        driverDataSource.setPassword("qq");
        driverDataSource.setUsername("root");
        driverDataSource.setDriver(new Driver());
        return driverDataSource;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
