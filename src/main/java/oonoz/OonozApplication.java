package oonoz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class OonozApplication {

	private static final Logger log = LoggerFactory.getLogger(OonozApplication.class);
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.username}")
	private String dataSourceUsername;

	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	public static void main(String[] args) {
		SpringApplication.run(OonozApplication.class, args);
	}
	
	
	 @Bean(name = "dataSource")
	    public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	        driverManagerDataSource.setDriverClassName(driverClassName);
	        driverManagerDataSource.setUrl(dataSourceUrl);
	        driverManagerDataSource.setUsername(dataSourceUsername);
	        driverManagerDataSource.setPassword(dataSourcePassword);
	        return driverManagerDataSource;
	    }
	 
}