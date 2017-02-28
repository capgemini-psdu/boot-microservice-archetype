#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.io.File;
import java.io.FilenameFilter;

import org.apache.catalina.valves.AccessLogValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class TomcatConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {
	
	private static final Logger logger = LoggerFactory.getLogger(TomcatConfig.class);
	
	private static final String BOOT_ACCESS = "boot-access";
	
	/** Day in milliseconds 24L * 60L * 60L * 1000L */
	private final long DAY_IN_MS = 86400000L;
	
	@Value("${symbol_dollar}{logging.path}") 
    String logDir;
	
	@Value("${symbol_dollar}{logging.access.expire: 5}")
	long daysBack;
	
	
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		
		if (container instanceof TomcatEmbeddedServletContainerFactory) {
            TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory) container;
            AccessLogValve accessLogValve = new AccessLogValve();
            accessLogValve.setDirectory(logDir);
            accessLogValve.setPrefix(BOOT_ACCESS);
            accessLogValve.setSuffix(".log");
            accessLogValve.setPattern("%h %l %u %t ${symbol_escape}"%r${symbol_escape}" %s %b %D");
            accessLogValve.setRenameOnRotate(true);
            factory.addContextValves(accessLogValve);
        } else {
            logger.error("Configuration of access log valve failed. Access log will be created in default location.");
        }
	}
	
	/**
	 * Scheduler to clean boot-access logs.
	 * Runs everyday at 1AM.
	 */
	@Scheduled(cron = "0 0 1 * * *")
	public void clearAccessLogFiles() {
		 
		logger.info("[Scheduler Task] Started clear boot access log files scheduler task");
		
		final File logFileDir= new File(logDir);
		
		if(!logFileDir.exists()){
			return;
		}
		
		final File[] accessLogFiles = logFileDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String fileName) {
				return fileName.startsWith(BOOT_ACCESS);
			}
		});
		
		long purgeTime = System.currentTimeMillis() - (daysBack * DAY_IN_MS);
	
		for(File accessLogFile : accessLogFiles){
			if(accessLogFile.lastModified() < purgeTime){
				logger.info("[Scheduler Task] Deleting access log file: {}",accessLogFile.getName());
				accessLogFile.delete();
			}
		}
	
		logger.info("[Scheduler Task] Completed clear boot access log files scheduler task");
	}
}