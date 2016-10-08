package oonoz.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * The Class SecurityConfig.
 * 
 * Description :
 * 		Configuration class for Spring Security framework.
 * 		Handle access to the different REST service.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user/authenticate").permitAll().and()
                .antMatchers(HttpMethod.GET, "/user/validation").permitAll()
                .antMatchers(HttpMethod.GET, "/user/forgotPassword").permitAll()
                .antMatchers(HttpMethod.POST,"/user/create").permitAll().and()
                .antMatchers(HttpMethod.GET, "/user/authenticate").permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .httpBasic();*/
        
		        .authorizeRequests()
		        .antMatchers(HttpMethod.OPTIONS,"/user/**").permitAll()	
		        .antMatchers(HttpMethod.POST, "/user/signupPlayer").permitAll()
		        .antMatchers(HttpMethod.GET, "/user/authenticate").hasRole("ADMIN").and()		        
		        .authorizeRequests()
		        .anyRequest().authenticated().and()
		        .httpBasic();

        //TODO For now I let everything pass, let's retrain this later
        /*http .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/event/**").permitAll()
                .antMatchers(HttpMethod.GET, "/event/**").permitAll()
                .antMatchers(HttpMethod.POST,"/event/**").permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .httpBasic();*/

       http.csrf().disable();
    }

    /** The data source. */
    @Autowired
    DataSource dataSource;

    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username , password, is_active as enabled " +
                                "from player " +
                                "where username=?")
                .authoritiesByUsernameQuery(
                "select username, role from authorities where username=?");
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
     * @param dataSource the new data source
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
