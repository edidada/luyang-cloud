package club.luyang.framework.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

/**
 * @author Luyang
 */
public class SecurityOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private static final Logger logger = LoggerFactory.getLogger(SecurityOpaqueTokenIntrospector.class);

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        logger.info(">>>进入到SecurityOpaqueTokenIntrospector拦截器");
        return null;
    }
}
