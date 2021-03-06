package oonoz.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The Class SecurityConfig.
 * 
 * Description : Configuration class for Spring Security framework. Handle
 * access to the different REST service.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/** The data source. */
	@Autowired
	DataSource dataSource;

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http    
		.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/user/**").permitAll()
		.antMatchers(HttpMethod.POST, "/user/signUpPlayer").permitAll()
		.antMatchers(HttpMethod.POST, "/user/signUpSupplier").permitAll()
		.antMatchers(HttpMethod.GET, "/user/requestSupplierStatus").permitAll()
		.antMatchers(HttpMethod.POST, "/user/generatePassword").permitAll()
		.antMatchers(HttpMethod.GET, "/user/validationMail").permitAll()
		.antMatchers(HttpMethod.PUT, "/admin/updatePlayer").permitAll()
		.antMatchers(HttpMethod.PUT, "/admin/updateSupplier").permitAll()
		.antMatchers(HttpMethod.DELETE, "/admin/deleteUser").permitAll()
		.antMatchers(HttpMethod.GET, "/admin/changeStatusUser").permitAll()
		.antMatchers(HttpMethod.POST, "/admin/filteredSearch").permitAll()
		.antMatchers(HttpMethod.GET, "/themes").permitAll()
		.antMatchers(HttpMethod.GET, "/validatedThemes").permitAll()
		.antMatchers(HttpMethod.POST, "/themes").permitAll()
		.antMatchers(HttpMethod.DELETE, "/themes/{id}").permitAll()
		.antMatchers(HttpMethod.PUT, "/themes/{id}").permitAll()
		.antMatchers(HttpMethod.GET, "/themes/{id}").permitAll()
		.antMatchers(HttpMethod.POST, "/themes/{id}/subthemes").permitAll()
		.antMatchers(HttpMethod.PUT, "/themes/{idTheme}/subthemes/{idSubTheme}").permitAll()
		.antMatchers(HttpMethod.DELETE, "/themes/{idTheme}/subthemes/{idSubTheme}").permitAll()
		.antMatchers(HttpMethod.GET, "/themes/{idTheme}/subthemes/{idSubTheme}").permitAll()
		.antMatchers(HttpMethod.PUT, "/themes/{idTheme}/subthemes/{idSubTheme}/validation").permitAll()
		.antMatchers(HttpMethod.PUT, "/themes/{id}/validation").permitAll()
		.antMatchers(HttpMethod.GET, "/themes/{idTheme}/subthemes").permitAll()
		.antMatchers(HttpMethod.GET, "/admin/getSupplierRequest").permitAll()
		.antMatchers(HttpMethod.DELETE,"/admin/refuseSupplierRequest").permitAll()
		.antMatchers(HttpMethod.POST,"/admin/acceptSupplierRequest").permitAll()
		.antMatchers(HttpMethod.POST,"/admin/createSupplierAccount").permitAll()
        .antMatchers(HttpMethod.POST,"/admin/createPlayerAccount").permitAll()
        .antMatchers(HttpMethod.GET, "/admin/getNotValidatedQCM").permitAll()
        .antMatchers(HttpMethod.GET, "/admin/getValidatedQCM").permitAll()
        .antMatchers(HttpMethod.PUT,"/admin/validateQCM").permitAll()
        .antMatchers(HttpMethod.DELETE, "/admin/deleteQCM").permitAll()
        .antMatchers(HttpMethod.GET,"/user/getSupplierQCM").permitAll()
        .antMatchers(HttpMethod.GET,"/searchSupplierQCM").permitAll()      
        .antMatchers(HttpMethod.GET, "/user/suppliers").permitAll()		
        .antMatchers(HttpMethod.GET, "/qcms").permitAll()
        .antMatchers(HttpMethod.POST, "/qcms").permitAll()
        .antMatchers(HttpMethod.POST, "/qcms/{idQCM}/questions").permitAll()
        .antMatchers(HttpMethod.DELETE, "/qcms/{id}").permitAll()
        .antMatchers(HttpMethod.GET, "/qcms/{idQCM}/questions/{idQuestion}").permitAll()
        .antMatchers(HttpMethod.GET, "/qcms/{id}/questions/questionNumber/{questionNumber}").permitAll()
        .antMatchers(HttpMethod.GET, "/qcms/{id}").permitAll()
        .antMatchers(HttpMethod.DELETE, "/qcms/{idQCM}/questions/{idQuestion}").permitAll()
        .antMatchers(HttpMethod.POST, "/qcms/{idQCM}/subthemes/{idSubTheme}").permitAll()
        .antMatchers(HttpMethod.DELETE, "/qcms/{idQCM}/subthemes/{idSubTheme}").permitAll()
        .antMatchers(HttpMethod.PUT, "/qcms/{id}").permitAll()
        .antMatchers(HttpMethod.PUT, "/qcms/{idQCM}/questions/{idQuestion}").permitAll()
        .antMatchers(HttpMethod.POST, "/qcms/filteredSearch").permitAll()
        .antMatchers(HttpMethod.GET, "/qcms/{idQcm}/winners").permitAll()
        .antMatchers(HttpMethod.PUT, "/qcms/{idQcm}/plays").permitAll()
        .antMatchers(HttpMethod.GET, "/qcms/{idQcm}/feedback").permitAll()
        .antMatchers(HttpMethod.POST, "/qcms/{idQCM}/validate").permitAll()
        .antMatchers(HttpMethod.POST, "/qcms/{idQCM}/invalidate").permitAll()
        .antMatchers(HttpMethod.GET, "/user/authenticate").hasRole("PLAYER").and()		        
		.authorizeRequests()
		.anyRequest().authenticated().and()
		.httpBasic();


		http.csrf().disable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(new ShaPasswordEncoder(256)).dataSource(dataSource)
		.usersByUsernameQuery("select mail, password,  is_active FROM player where mail = ?")
		.authoritiesByUsernameQuery(
				"select username, role from authorities where username=(select username from player where mail = ?)");

		auth.jdbcAuthentication().passwordEncoder(new ShaPasswordEncoder(256)).dataSource(dataSource)
		.usersByUsernameQuery("select username, password, is_active from player where username=?")
		.authoritiesByUsernameQuery("select username, role from authorities where username=?");
	}

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Sets the data source.
	 *
	 * @param dataSource
	 *            the new data source
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
