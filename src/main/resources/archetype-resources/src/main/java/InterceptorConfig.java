#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.capgemini.psdu.presentation.HeaderAmenderInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Bean
	public HeaderAmenderInterceptor headerAmenderInterceptor() {
		return new HeaderAmenderInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(headerAmenderInterceptor());
	}

}
