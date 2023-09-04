package club.luyang.platform.auth.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Map;

public abstract class AbstractAuthenticationProvider<T extends AbstractGrantAuthenticationToken> implements AuthenticationProvider {

	public AbstractAuthenticationProvider() {
	}

	public abstract UsernamePasswordAuthenticationToken buildToken(Map<String, Object> reqParameters);

	@Override
	public abstract boolean supports(Class<?> authentication);


	public abstract void checkClient(RegisteredClient registeredClient);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return null;
	}
}
