package club.luyang.platform.auth.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public abstract class AbstractGrantAuthenticationToken extends AbstractAuthenticationToken {

	public AbstractGrantAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
	}
}
