package com.vr.rental.config.idwork;

import com.vr.rental.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import com.vr.common.core.idwork.SnowflakeIdWorker;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author ys
 * @Date 2023/4/28 21:43
 */
@Slf4j
public class IdGen {

    private static SnowflakeIdWorker snowflakeIdWorker = null;

    private static final Lock lock = new ReentrantLock();

    public static Long genId() {
        if (snowflakeIdWorker == null) {
            try {
                lock.lock();
                if (snowflakeIdWorker == null) {
                    snowflakeIdWorker = SpringUtils.getBean("snowflakeIdWorker", SnowflakeIdWorker.class);
                }
            } catch (NoSuchBeanDefinitionException e) {
                log.error("ID生成异常", e);
                throw e;
            } finally {
                lock.unlock();
            }

        }
        return snowflakeIdWorker.nextId();
    }

}
