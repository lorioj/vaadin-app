package com.example.appservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.appservice.service.UserService;

@Configuration
public class AppServiceConfig {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private CustomAuthenticationProvider customAuthenticationProvider;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		System.err.println(jwtSecret);
		JwtAutorizationFilter jwtFilter = new JwtAutorizationFilter(userService, jwtSecret);
		http.authorizeHttpRequests(request -> request.requestMatchers("/api/authenticate", "/api/users").authenticated())
				.csrf(csrf -> csrf.disable()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
//		http.csrf().disable()
//		.authorizeHttpRequests()
//		.requestMatchers("/api/users").permitAll()
//		.and()
//		.authorizeHttpRequests().requestMatchers("/api/hello")
//		.authenticated().and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();

		return http.build();

	}
	


//	//Basic Auth
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(request -> request.requestMatchers("/api/users").authenticated())
//				.csrf(csrf -> csrf.disable()).httpBasic(Customizer.withDefaults());
//		return http.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	@Bean
//	public UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
//		String pass = passwordEncoder.encode("abc123");
//		User.UserBuilder users = User.builder();
//		UserDetails john = users.username("john").password(pass).roles() // No roles for
//																										// now
//				.build();
//		return new InMemoryUserDetailsManager(john);
//	
//	}
//	End of Auth
	
//	@Bean
//	public void authBuilder(AuthenticationManagerBuilder b) {
//		b.authenticationProvider(customAuthenticationProvider);
//	}
	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//		return authenticationConfiguration.getAuthenticationManager();
//	}

}
