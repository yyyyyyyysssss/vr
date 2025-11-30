package com.vr.rental.utils;

import com.vr.common.core.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class AesUtilsTest {

    private static final String TARGET_DATA = "{\"id\":123,\"name\":\"xxx\"}";

    @Test
    void getKeyPair() throws Exception {
        String key = AesUtils.generateRandomKey();
        log.info("aes key: {}",key);
    }

    @Test
    void encrypt() throws Exception {
        String key = AesUtils.generateRandomKey();
        String encrypt = AesUtils.encrypt(TARGET_DATA, key);
        log.info("encrypt : {}",encrypt);
    }

    @Test
    void decrypt() throws Exception {
        String key = AesUtils.generateRandomKey();
        String encrypt = AesUtils.encrypt(TARGET_DATA, key);
        String decrypt = AesUtils.decrypt(encrypt, key);
        log.info("decrypt : {}",decrypt);
    }

}
