package club.luyang.platform.auth.config.authorization;

import cn.hutool.core.lang.Assert;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Component;

/**
 * 授权信息存储
 * 官方文档：<a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-authorization-service">...</a>
 * @author Luyang
 */
@Component
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        
    }

    @Override
    public void remove(OAuth2Authorization authorization) {

    }

    @Override
    public OAuth2Authorization findById(String id) {
        return null;
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return null;
    }
}
