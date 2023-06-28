package club.luyang.platform.auth.config.authorization;

import cn.hutool.core.lang.Assert;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 授权信息存储
 * 官方文档：<a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-authorization-service">...</a>
 *
 * @author Luyang
 */
@Component
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private static final Map<String, OAuth2Authorization> MAP_POOL = new ConcurrentHashMap<>();

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");

        if (isState(authorization)) {
            String token = authorization.getAttribute(OAuth2ParameterNames.STATE);
            MAP_POOL.put(buildKey(OAuth2ParameterNames.STATE, token), authorization);
        }

        if (isCode(authorization)) {
            OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
                    .getToken(OAuth2AuthorizationCode.class);
            OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
            MAP_POOL.put(buildKey(OAuth2ParameterNames.CODE, authorizationCodeToken.getTokenValue()), authorization);
        }

        if (isRefreshToken(authorization)) {
            OAuth2RefreshToken refreshToken = authorization.getRefreshToken().getToken();
            MAP_POOL.put(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue()), authorization);
        }

        if (isAccessToken(authorization)) {
            OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
            MAP_POOL.put(buildKey(OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue()), authorization);
        }
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");

        List<String> keys = new ArrayList<>();
        if (isState(authorization)) {
            String token = authorization.getAttribute(OAuth2ParameterNames.STATE);
            keys.add(buildKey(OAuth2ParameterNames.STATE, token));
        }

        if (isCode(authorization)) {
            OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
                    .getToken(OAuth2AuthorizationCode.class);
            OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
            keys.add(buildKey(OAuth2ParameterNames.CODE, authorizationCodeToken.getTokenValue()));
        }

        if (isRefreshToken(authorization)) {
            OAuth2RefreshToken refreshToken = authorization.getRefreshToken().getToken();
            keys.add(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue()));
        }

        if (isAccessToken(authorization)) {
            OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
            keys.add(buildKey(OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue()));
        }

        keys.forEach(MAP_POOL::remove);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return null;
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return MAP_POOL.get(buildKey(tokenType.getValue(), token));
    }

    private String buildKey(String type, String id) {
        return String.format("%s::%s::%s", "token", type, id);
    }

    private static boolean isState(OAuth2Authorization authorization) {
        return Objects.nonNull(authorization.getAttribute(OAuth2ParameterNames.STATE));
    }

    private static boolean isCode(OAuth2Authorization authorization) {
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
                .getToken(OAuth2AuthorizationCode.class);
        return Objects.nonNull(authorizationCode);
    }

    private static boolean isRefreshToken(OAuth2Authorization authorization) {
        return Objects.nonNull(authorization.getRefreshToken());
    }

    private static boolean isAccessToken(OAuth2Authorization authorization) {
        return Objects.nonNull(authorization.getAccessToken());
    }
}
