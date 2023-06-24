package club.luyang.platform.auth.config.token;

import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * 自定义OAuth2Token属性
 * 官方文档：<a href="https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#oauth2-token-customizer">...</a>
 *
 * @author Luyang
 */
public class TokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {

    @Override
    public void customize(OAuth2TokenClaimsContext context) {
        OAuth2TokenClaimsSet.Builder claims = context.getClaims();
        claims.claim("license", "https://blog.luyang.club/");
        claims.claim("client_id", context.getAuthorizationGrant().getName());
    }
}
