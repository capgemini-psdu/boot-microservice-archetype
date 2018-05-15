#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableScheduling
@Import({ApplicationConfig.class, BootConfig.class, SwaggerConfig.class, SecurityConfig.class, InterceptorConfig.class })
public class Application {

	public static void main(String[] args) {
	    SpringApplication.run(Application.class, args);
	}
}