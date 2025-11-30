package com.vr.rental.config.redis;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vr.rental.config.security.RequestUrlAuthority;
import com.vr.rental.config.security.authentication.email.EmailAuthenticationToken;
import com.vr.rental.config.security.authentication.ott.OneTimeTokenAuthenticationTokenMixin;
import com.vr.rental.config.security.authentication.refreshtoken.RefreshAuthenticationToken;
import com.vr.rental.config.security.oauth2.OAuthClientAuthenticationToken;
import com.vr.rental.domain.entity.AuthorityUrl;
import com.vr.rental.domain.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationToken;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.web.jackson2.WebServletJackson2Module;

/**
 * @Description
 * @Author ys
 * @Date 2024/7/8 16:59
 */
@Configuration
public class RedisTemplateConfig {

    @Bean
    public <T> RedisTemplate<String, T> authRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(authRedisSerializer());
        redisTemplate.setHashValueSerializer(authRedisSerializer());

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisSerializer<Object> authRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new CoreJackson2Module());
        objectMapper.registerModule(new WebServletJackson2Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.addMixIn(RequestUrlAuthority.class, RequestUrlAuthority.RequestUrlAuthorityMixin.class);
        objectMapper.addMixIn(AuthorityUrl.class, AuthorityUrl.AuthorityUrlMixin.class);
        objectMapper.addMixIn(OAuthClientAuthenticationToken.class, OAuthClientAuthenticationToken.OAuthClientAuthenticationTokenMixin.class);
        objectMapper.addMixIn(EmailAuthenticationToken.class, EmailAuthenticationToken.EmailAuthenticationTokenMixin.class);
        objectMapper.addMixIn(RefreshAuthenticationToken.class, RefreshAuthenticationToken.RefreshAuthenticationTokenMixin.class);
        objectMapper.addMixIn(OneTimeTokenAuthenticationToken.class, OneTimeTokenAuthenticationTokenMixin.class);
        objectMapper.addMixIn(User.class, User.UserMixin.class);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

}
