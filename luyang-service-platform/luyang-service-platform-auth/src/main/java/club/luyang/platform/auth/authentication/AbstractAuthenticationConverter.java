package club.luyang.platform.auth.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.Map;
import java.util.Set;


public abstract class AbstractAuthenticationConverter<T extends AbstractGrantAuthenticationToken> implements AuthenticationConverter {

	public abstract boolean support(String grantType);

	public abstract void checkParams(HttpServletRequest request);

	public abstract T buildToken(Authentication clientPrincipal, Set<String> requestedScopes, Map<String, Object> additionalParameters);

	@Override
	public Authentication convert(HttpServletRequest request) {

		return null;
	}
}
