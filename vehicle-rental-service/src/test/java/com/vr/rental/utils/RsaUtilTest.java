package com.vr.rental.utils;

import com.vr.common.core.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class RsaUtilTest {

    private static final String TARGET_DATA = "{\"id\":123,\"name\":\"xxx\"}";

    @Test
    void getKeyPair() throws Exception {
        RsaUtils.KeyPairValue keyPairValue = RsaUtils.generateKeyPair();
        String publicKey = keyPairValue.getPublicKey();
        String privateKey = keyPairValue.getPrivateKey();
        log.info("generate publicKey: {}",publicKey);
        log.info("generate privateKey: {}",privateKey);
        String encrypt = RsaUtils.encrypt(TARGET_DATA, publicKey, RsaUtils.PaddingMode.OAEP_SHA1);
        String decrypt = RsaUtils.decrypt(encrypt, privateKey, RsaUtils.PaddingMode.OAEP_SHA1);
        assertEquals(decrypt,TARGET_DATA);
    }

    @Test
    void encrypt() throws Exception {
        String encrypt = RsaUtils.encrypt(TARGET_DATA);
        log.info("encrypt : {}",encrypt);
        assertNotNull(encrypt);
    }

    @Test
    void decrypt() throws Exception {
        int keySize = 1024;
        RsaUtils.KeyPairValue keyPairValue = RsaUtils.generateKeyPair(keySize);
        String publicKey = keyPairValue.getPublicKey();
        String privateKey = keyPairValue.getPrivateKey();
        String encrypt = RsaUtils.encrypt(TARGET_DATA,publicKey, RsaUtils.PaddingMode.OAEP_MD5,keySize);
        String decrypt = RsaUtils.decrypt(encrypt,privateKey, RsaUtils.PaddingMode.OAEP_MD5,keySize);
        log.info("decrypt : {}",decrypt);
        assertNotNull(decrypt);
    }

    @Test
    void sign() throws Exception {
        String sign = RsaUtils.sign(TARGET_DATA);
        log.info("sign : {}",sign);
        assertNotNull(sign);
    }

    @Test
    void verify() throws Exception {
        String sign = RsaUtils.sign(TARGET_DATA);
        boolean verify = RsaUtils.verify(TARGET_DATA, sign);
        log.info("verify : {}",verify);
        assertTrue(verify);
    }

    @Test
    void loadLocalPrivateKey(){
        String privateKey = RsaUtils.loadLocalPrivateKeyStr();
        log.info("privateKey: {}",privateKey);
    }

    @Test
    void loadLocalPublicKey(){
        String publicKey = RsaUtils.loadLocalPublicKeyStr();
        log.info("publicKey: {}",publicKey);
    }

}