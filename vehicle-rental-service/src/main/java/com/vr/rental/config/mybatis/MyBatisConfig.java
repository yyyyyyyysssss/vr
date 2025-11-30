package com.vr.rental.config.mybatis;

import com.vr.common.mybatis.handler.BaseMetaHandler;
import com.vr.rental.context.SecurityUserContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ys
 * @Date 2023/7/1 14:51
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public BaseMetaHandler baseMetaHandler(){

        return new BaseMetaHandler(new SecurityUserContext());
    }

}
