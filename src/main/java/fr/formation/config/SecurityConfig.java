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
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/avionnage/**").permitAll()
		.antMatchers("/composer-avion/**").hasRole("ADMIN")
		.antMatchers("/composer-vol/**").hasRole("ADMIN")
		.antMatchers("/**").hasAnyRole("ADMIN", "SECRETAIRE")
		.and()
		.formLogin();	//	Tout ça c'est pour une page d'autentification personalisée. pas utilisé pour le moment
		//	.loginPage("/connect") //lien vers getmapping --> HomeController
		//	.loginProcessingUrl("/connect") //lien du formunaire en post !!!
		//	.defaultSuccessUrl("/composer-avion", true)
		//	.failureUrl("/connect?error=true")
		//	.permitAll();
		
		//	pour jeton CSRF :
		// <input type="hidden" th:name="${_csrf.parameterName}" th:value="{_csrf.token}"/>
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authService);
	//	Si jamais doit passer par connection directe et non pas BDD
	//	auth.inMemoryAuthentication()
	//	 .withUser("admin").password("{noop}admin").roles("ADMIN")
	//	.and()
	//	.withUser("secre").password("{noop}secre").roles("SECRETAIRE");
	}*/
	
	
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**")
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/**").permitAll()
				.and().httpBasic()
				.and().csrf().disable()
				;
		}
	}
	
	
	@Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		 @Override
	        protected void configure(HttpSecurity http) throws Exception {
		        http.authorizeRequests()
		        .antMatchers("/assets/**").permitAll()
				.antMatchers("/avionnage/**").permitAll()
				.antMatchers("/composer-avion/**").hasRole("ADMIN")
				.antMatchers("/composer-vol/**").hasRole("ADMIN")
				.antMatchers("/**").hasAnyRole("ADMIN", "SECRETAIRE")
				.and()
				.formLogin()
				//	.loginPage("/connect") //Lien vers le @GetMapping
				//	.loginProcessingUrl("/connect") //Lien du POST du form html, c'est Spring qui crée un @PostMapping("/connect")
				//	.defaultSuccessUrl("/composer-vol") 
				//	.failureUrl("/connect?error=true") //Page d'erreur de connexion
				//	.permitAll()
				.and().csrf().disable() //Si vous voulez désactiver la protection CSRF
				;
		 }
	}
	
	
	

	
}
