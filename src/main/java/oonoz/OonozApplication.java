package oonoz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * The Class OonozApplication.
 * 
 * Description :
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class OonozApplication extends SpringBootServletInitializer {

	/** The driver class name. */
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	/** The data source url. */
	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	/** The data source username. */
	@Value("${spring.datasource.username}")
	private String dataSourceUsername;

	/** The data source password. */
	@Value("${spring.datasource.password}")
	private String dataSourcePassword;

	/**
	 * Data source.
	 *
	 * @return the driver manager data source
	 */
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(driverClassName);
		driverManagerDataSource.setUrl(dataSourceUrl);
		driverManagerDataSource.setUsername(dataSourceUsername);
		driverManagerDataSource.setPassword(dataSourcePassword);
		return driverManagerDataSource;
	}
	
	/**
	 * Datatype hibernate module.
	 *
	 * @return the module
	 */
	@Bean
	public Module datatypeHibernateModule() {
	  return new Hibernate4Module();
	}

    /* (non-Javadoc)
     * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OonozApplication.class);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(OonozApplication.class, args);
    }
	
}