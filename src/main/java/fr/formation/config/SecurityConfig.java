package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("!dev")
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**")
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/**").hasAnyRole("ADMIN", "MEDECIN")
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
				.antMatchers("/medecin/**").hasAnyRole("MEDECIN", "ADMIN")
				.antMatchers("/**").hasAnyRole("ADMIN", "SECRETAIRE")
				.and()
				.formLogin()
				.loginPage("/connect") //Lien vers le @GetMapping
				.loginProcessingUrl("/connect") //Lien du POST du form html, c'est Spring qui crée un @PostMapping("/connect")
				.defaultSuccessUrl("/visite") //Page par défaut après connexion
				.failureUrl("/connect?error=true") //Page d'erreur de connexion
				.permitAll()
				.and().csrf().disable() //Si vous voulez désactiver la protection CSRF
				;
		 }
	}
	
	
	/*
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/medecin/**").hasAnyRole("MEDECIN", "ADMIN")
		.antMatchers("/secretaire/**").hasAnyRole("SECRETAIRE", "ADMIN")
		.antMatchers("/visite/**").hasAnyRole("SECRETAIRE", "MEDECIN", "ADMIN")
		.antMatchers("/patient/**").hasAnyRole("SECRETAIRE", "MEDECIN", "ADMIN")
		.antMatchers("/**").hasRole("ADMIN")
		.and()
		.formLogin()
			.loginPage("/connect") //lien vers getmapping --> HomeController
			.loginProcessingUrl("/connect") //lien du formunaire en post !!!
			.defaultSuccessUrl("/visite", true)
			.failureUrl("/connect?error=true")
			.permitAll();
	}*/

	
}
