package com.vr.rental.config.idwork;

import com.vr.common.core.idwork.RandomWorkIdService;
import com.vr.common.core.idwork.SnowflakeIdWorker;
import com.vr.common.core.idwork.WorkIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ys
 * @Date 2024/4/28 11:10
 */
@Configuration
public class IdConfig {


    @Bean
    public WorkIdService workIdService(){

        return new RandomWorkIdService();
    }

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(WorkIdService workIdService){

        return new SnowflakeIdWorker(workIdService);
    }

}
