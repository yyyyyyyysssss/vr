package com.vr.rental.config.security;

import com.vr.rental.enums.ClientType;
import com.vr.rental.domain.entity.TokenInfo;
import com.vr.rental.domain.entity.User;
import com.vr.rental.enums.TokenType;
import com.vr.rental.utils.PayloadInfo;
import groovy.lang.Tuple2;


public interface TokenService {

    TokenInfo generate(User user, ClientType clientType, boolean rememberMe);

    void revokeToken(String token);

    Tuple2<Boolean, PayloadInfo> isValid(String token, TokenType tokenType);

}
