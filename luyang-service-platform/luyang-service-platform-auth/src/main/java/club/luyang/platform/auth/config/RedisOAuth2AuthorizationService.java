package club.luyang.platform.auth.config;

import club.luyang.boot.cache.helper.RedisHelper;
import cn.hutool.core.lang.Assert;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 授权信息存储
 * 官方文档：<a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-authorization-service">...</a>
 *
 * @author Luyang
 */
@Component
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");

        if (isState(authorization)) {
            String token = authorization.getAttribute(OAuth2ParameterNames.STATE);
            RedisHelper.set(buildKey(OAuth2ParameterNames.STATE, token), authorization, 10L, TimeUnit.MINUTES);
        }

        if (isCode(authorization)) {
            OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
                    .getToken(OAuth2AuthorizationCode.class);
            OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
            long between = ChronoUnit.MINUTES.between(Objects.requireNonNull(authorizationCodeToken.getIssuedAt()),
                    authorizationCodeToken.getExpiresAt());
            RedisHelper.set(
                    buildKey(OAuth2ParameterNames.CODE, authorizationCodeToken.getTokenValue()),
                    authorization,
                    between,
                    TimeUnit.MINUTES);
        }

        if (isRefreshToken(authorization)) {
            OAuth2RefreshToken refreshToken = Objects.requireNonNull(authorization.getRefreshToken()).getToken();
            long between = ChronoUnit.SECONDS.between(Objects.requireNonNull(refreshToken.getIssuedAt()), refreshToken.getExpiresAt());
            RedisHelper.set(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue()), authorization,
                    between,
                    TimeUnit.SECONDS);

            // MAP.put(buildKey(OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue()), authorization);
        }

        if (isAccessToken(authorization)) {
            OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
            long between = ChronoUnit.SECONDS.between(Objects.requireNonNull(accessToken.getIssuedAt()), accessToken.getExpiresAt());
            RedisHelper.set(
                    buildKey(OAuth2ParameterNames.ACCESS_TOKEN, accessToken.getTokenValue()),
                    authorization,
                    between,
                    TimeUnit.SECONDS);
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

        RedisHelper.getRedisTemplate().delete(keys);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return null;
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return RedisHelper.get(buildKey(tokenType.getValue(), token));
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
