package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.formation.security.AuthService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Profile("!dev")
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
	//Necessaire si passe par login et mdp dans BDD
	@Autowired
	AuthService authService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	//	return NoOpPasswordEncoder.getInstance();	//	Sans cryptage
		return new BCryptPasswordEncoder();	//	Avec cryptage
	}
	
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**")
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/**").permitAll()
				.and().httpBasic()
				.and().csrf().disable();
		}
	}
	
	
	@Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		 @Override
	        protected void configure(HttpSecurity http) throws Exception {
		        http.authorizeRequests()
		        .antMatchers("/assets/**").permitAll()
				.antMatchers("/avionnage/**").permitAll()
				.antMatchers("/connect/**").permitAll()
				.antMatchers("/composer-avion/**").hasRole("ADMIN")
				.antMatchers("/composer-vol/**").hasRole("ADMIN")
				.antMatchers("/**").hasAnyRole("ADMIN", "SECRETAIRE")
				.and()
				.formLogin()
				.and().csrf().disable()
				;
		 }
	}
	
	
	

	
}
