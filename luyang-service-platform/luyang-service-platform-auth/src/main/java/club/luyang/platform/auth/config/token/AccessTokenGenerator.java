package club.luyang.platform.auth.config.token;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContext;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * 自定义 OAuth2Token 生成
 * 官方文档：<a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-token-generator">...</a>
 *
 * @author Luyang
 */
public class AccessTokenGenerator implements OAuth2TokenGenerator<OAuth2AccessToken> {

    private OAuth2TokenCustomizer<OAuth2TokenClaimsContext> accessTokenCustomizer;

    public void setAccessTokenCustomizer(OAuth2TokenCustomizer<OAuth2TokenClaimsContext> accessTokenCustomizer) {
        Assert.notNull(accessTokenCustomizer, "accessTokenCustomizer cannot be null");
        this.accessTokenCustomizer = accessTokenCustomizer;
    }

    @Override
    public OAuth2AccessToken generate(OAuth2TokenContext context) {

        RegisteredClient registeredClient = context.getRegisteredClient();
        OAuth2TokenType tokenType = context.getTokenType();
        if (!OAuth2TokenType.ACCESS_TOKEN.equals(tokenType) || !OAuth2TokenFormat.REFERENCE.equals(registeredClient.getTokenSettings().getAccessTokenFormat())) {
            return null;
        }

        AuthorizationServerContext authorizationServerContext = context.getAuthorizationServerContext();
        Set<String> authorizedScopes = context.getAuthorizedScopes();
        Authentication authentication = context.getPrincipal();

        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(registeredClient.getTokenSettings().getAccessTokenTimeToLive());

        OAuth2TokenClaimsSet.Builder claimsBuilder = OAuth2TokenClaimsSet.builder();
        claimsBuilder
                .subject(authentication.getName())
                .audience(Collections.singletonList(registeredClient.getClientId()))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .notBefore(issuedAt)
                .id(UUID.randomUUID().toString());

        if (null != authorizationServerContext) {
            claimsBuilder.issuer(authorizationServerContext.getIssuer());
        }

        if (CollUtil.isNotEmpty(authorizedScopes)) {
            claimsBuilder.claim(OAuth2ParameterNames.SCOPE, authorizedScopes);
        }

        if (null != this.accessTokenCustomizer) {
            OAuth2TokenClaimsContext.Builder accessTokenContextBuilder = OAuth2TokenClaimsContext
                    .with(claimsBuilder)
                    .registeredClient(registeredClient)
                    .principal(authentication)
                    .authorizationServerContext(authorizationServerContext)
                    .authorizedScopes(authorizedScopes)
                    .tokenType(tokenType)
                    .authorizationGrantType(context.getAuthorizationGrantType());

            OAuth2Authorization authorization = context.getAuthorization();
            if (null != authorization) {
                accessTokenContextBuilder.authorization(authorization);
            }

            Authentication authorizationGrant = context.getAuthorizationGrant();
            if (null != authorizationGrant) {
                accessTokenContextBuilder.authorizationGrant(authorizationGrant);
            }

            OAuth2TokenClaimsContext accessTokenContext = accessTokenContextBuilder.build();
            this.accessTokenCustomizer.customize(accessTokenContext);
        }

        OAuth2TokenClaimsSet accessTokenClaimsSet = claimsBuilder.build();
        return new AccessTokenGenerator.AccessTokenClaims(
                OAuth2AccessToken.TokenType.BEARER,
                IdUtil.nanoId(32),
                accessTokenClaimsSet.getIssuedAt(),
                accessTokenClaimsSet.getExpiresAt(),
                authorizedScopes,
                accessTokenClaimsSet.getClaims()
        );
    }

    private static final class AccessTokenClaims extends OAuth2AccessToken implements ClaimAccessor {

        private final Map<String, Object> claims;

        private AccessTokenClaims(TokenType tokenType, String tokenValue, Instant issuedAt, Instant expiresAt,
                                  Set<String> scopes, Map<String, Object> claims) {
            super(tokenType, tokenValue, issuedAt, expiresAt, scopes);
            this.claims = claims;
        }

        @Override
        public Map<String, Object> getClaims() {
            return this.claims;
        }
    }
}
