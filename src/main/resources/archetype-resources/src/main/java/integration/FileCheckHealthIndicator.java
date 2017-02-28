#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.integration;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;

public class FileCheckHealthIndicator extends AbstractHealthIndicator {
	
	private String path;
	
	@Autowired
    public FileCheckHealthIndicator(String path) {
		this.path = path;
	}

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		
		File file = new File(path);
		if(file.exists()) {
			builder.up();
		} else {
			builder.down();
		}
	}
}