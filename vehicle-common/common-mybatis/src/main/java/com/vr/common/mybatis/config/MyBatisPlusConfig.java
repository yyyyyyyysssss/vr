package com.vr.common.mybatis.config;


import com.vr.common.mybatis.mapper.MySqlInjector;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ys
 * @Date 2023/7/1 14:51
 */
@AutoConfiguration
@Configuration
public class MyBatisPlusConfig {


    @Bean
    public MySqlInjector mySqlInjector(){

        return new MySqlInjector();
    }

}
