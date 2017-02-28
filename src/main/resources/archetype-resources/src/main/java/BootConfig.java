#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

import com.capgemini.psdu.httplogger.filter.HttpRequestResponseFilter;
import com.capgemini.psdu.presentation.LoggingResponseEntityExceptionHandler;
import ${package}.integration.FileCheckHealthIndicator;

@Configuration
public class BootConfig {

	@Value("${symbol_dollar}{graphite.host}")
	private String graphiteHost;

	@Value("${symbol_dollar}{graphite.port}")
	private int graphitePort;
	
	@Value("${symbol_dollar}{keepalive.location}")
	private String keepAliveLocation;

	@Autowired
	private MetricRegistry metricRegistry;
	
	@Bean
	public LoggingResponseEntityExceptionHandler exceptionHandler() {		
		return new LoggingResponseEntityExceptionHandler();
	}

	/** Uncomment in environments where Graphite is present
	@Bean
	public Graphite createGraphite() {
		return new Graphite(new InetSocketAddress(graphiteHost, graphitePort));
	}

	@Bean
    public GraphiteReporter createGraphiteReporter() {
          final GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry)
        		  .prefixedWith("${microserviceGroupName}"+"-"+"${microserviceName}")
        		  .convertRatesTo(TimeUnit.SECONDS)
        		  .convertDurationsTo(TimeUnit.MILLISECONDS)
        		  .filter(MetricFilter.ALL)
        		  .build(createGraphite());

          reporter.start(5, TimeUnit.SECONDS);      
          return reporter;
    }
	*/
	
	@Bean
	public FileCheckHealthIndicator fileCheckHealthIndicator() {		
		return new FileCheckHealthIndicator(keepAliveLocation);
	}

	/**
	 * Stops Spring treating 4xx and 5xx received from outbound REST calls as exceptions.  
	 */
	@Bean
	public ResponseErrorHandler getResponseErrorHandler() {
		return new DefaultResponseErrorHandler() {
			@Override
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		};
	}
	
	/**
	 * Allows logging of request and response via logback.xml setting
	 */
	@Bean
	public Filter httpRequestResponseFilter(){
		return new HttpRequestResponseFilter();
	}
}