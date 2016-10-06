package oonoz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import oonoz.domain.Supplier;
import oonoz.repository.SupplierRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class OonozApplication {

	private static final Logger log = LoggerFactory.getLogger(OonozApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(OonozApplication.class, args);
	}
	
	
	 @Bean(name = "dataSource")
	    public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
	        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/oonoz");
	        driverManagerDataSource.setUsername("postgres");
	        driverManagerDataSource.setPassword("admin");
	        return driverManagerDataSource;
	    }
	 
}