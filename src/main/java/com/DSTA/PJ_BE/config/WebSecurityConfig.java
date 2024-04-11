package com.DSTA.PJ_BE.config;

import com.DSTA.PJ_BE.Security.JwtAuthenticationFilter;
import com.DSTA.PJ_BE.Security.JwtConfigurer;
import com.DSTA.PJ_BE.Security.JwtTokenProvider;
import com.DSTA.PJ_BE.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@SuppressWarnings("deprecation")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Lazy
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter(tokenProvider, userService);
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().disable();
		http
		.cors().configurationSource(corsConfigurationSource())
		.and()
		.csrf().disable()
        .headers()
    .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
        .authorizeRequests()
        .antMatchers("/api/public/**").permitAll()
        .antMatchers("/api/account/login").permitAll()
		.antMatchers("/api/account/register").permitAll()
		.antMatchers("/api/product/get-product-id/{id}").permitAll()
		.antMatchers("/api/categories/get-all-category").permitAll()
		.antMatchers("/api/product/get-all-product").permitAll()
		.antMatchers("/api/account/sendMail/{email}").permitAll()
		.antMatchers("/api/account/verifyOtp/{otp}").permitAll()
		.antMatchers("/api/account/createNewPass/{newPass}").permitAll()
        .anyRequest().authenticated()
        .and().logout().logoutUrl("/api/logout").permitAll()
    .and()
        .formLogin().disable()
        .httpBasic().disable() 
        .exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            })
     .and()
        .apply(securityConfigurerAdapter());
	}
	
	private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider, userService);
    }
	
	private CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		var corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setExposedHeaders(List.of("Authorization"));
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
