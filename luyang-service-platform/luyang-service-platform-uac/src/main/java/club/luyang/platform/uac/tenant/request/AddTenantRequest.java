package club.luyang.platform.uac.tenant.request;

import jakarta.validation.constraints.NotBlank;

/**
 * @author lu_yang
 */
public record AddTenantRequest(
	@NotBlank
	String name
) {}
