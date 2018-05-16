#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')
package ${package};

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

	private static final String ROLE_USER = "USER";

	private static final String ROLE_ADMIN = "ADMIN";

	@Value("${symbol_dollar}{appsecurity.admin.username}")
	private String adminUsername;

	@Value("${symbol_dollar}{appsecurity.admin.password}")
	private String adminPassword;

	@Value("${symbol_dollar}{appsecurity.user.username}")
	private String userUsername;

	@Value("${symbol_dollar}{appsecurity.user.password}")
	private String userPassword;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			//added the {noop}for plain text password .it is NOT SECURE so it is not recommended for production environments.
			.withUser(adminUsername).password("{noop}"+adminPassword).roles(ROLE_ADMIN)
			.and()
			.withUser(userUsername).password("{noop}" +userPassword).roles(ROLE_USER);
	}
}