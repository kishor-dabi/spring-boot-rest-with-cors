package com.springboot.app.config;

//import com.springboot.app.security.CorsFilter;
//import com.springboot.app.security.CustomUserDetailsService;
//import com.springboot.app.security.JwtAuthenticationEntryPoint;
//import com.springboot.app.security.JwtAuthenticationFilter;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.session.SessionManagementFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.CorsUtils;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return  new JwtAuthenticationFilter();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//   
//    @Bean
//    CorsFilter corsFilter() {
//        CorsFilter filter = new CorsFilter();
//        return filter;
//    }
//  
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////      addFilterBefore(corsFilters(), null)
////.cors().and() 
//    	
////    	http.addFilterBefore(corsFilter(), SessionManagementFilter.class);
//    	
//    	System.out.println("____________________________________");
//    	http
//    	.addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                .csrf().disable()
//                
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isCorsRequest).permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                .antMatchers("/api/v1/auth/**").permitAll()
//                .antMatchers("/v2/api-docs/**").permitAll()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/webjars/**").permitAll()
////                .antMatchers(HttpMethod.OPTIONS, "/*").permitAll() 
//                .anyRequest()
//                .authenticated() .and()
//                .httpBasic()
//                .and()                
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//               ;
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    //    @Override
////    @Bean
////    protected UserDetailsService userDetailsService() {
////        UserDetails ramesh = User.builder().username("ramesh").password(passwordEncoder()
////                .encode("password")).roles("USER").build();
////        UserDetails admin = User.builder().username("admin").password(passwordEncoder()
////                .encode("admin")).roles("ADMIN").build();
////        return new InMemoryUserDetailsManager(ramesh, admin);
////    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.app.security.CorsFilter;
import com.springboot.app.security.CustomUserDetailsService;
import com.springboot.app.security.JwtAuthenticationEntryPoint;
import com.springboot.app.security.JwtAuthenticationFilter;

import io.swagger.models.HttpMethod;

//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//  @Autowired
//  private CustomUserDetailsService userDetailsService;
//
//  @Autowired
//  private JwtAuthenticationEntryPoint authenticationEntryPoint;
//
//  @Bean
//  public JwtAuthenticationFilter jwtAuthenticationFilter(){
//      return  new JwtAuthenticationFilter();
//  }
////  
////  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
////  public AuthenticationManager authenticationManagerBean() throws Exception {
////      return super.authenticationManagerBean();
////  }
//  
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.userDetailsService(userDetailsService);
//  }
//
//  @Bean
//  PasswordEncoder passwordEncoder(){
//      return new BCryptPasswordEncoder();
//  }
//
//	@Bean
//	CorsFilter corsFilter() {
//	    CorsFilter filter = new CorsFilter();
//	    return filter;
//	}
//
//  	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
////  		http.authorizeRequests().antMatchers("/login").permitAll()
////  		.antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
////  		.hasAnyAuthority("Admin", "Editor", "Salesperson")
////  		.hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
////  		.anyRequest().authenticated()
////  		.and().formLogin()
////  		.loginPage("/login")
////                    .usernameParameter("email")
////                    .permitAll()
////                .and()
////                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
////                .and()
////                .logout().permitAll();
//// 
////        http.headers().frameOptions().sameOrigin();
//// 
////        return http.build();
//
//  		http.cors().and()
//    	.addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                .csrf(csrf -> csrf.disable())
//                
//                .authorizeRequests()
////                .requestMatchers(CorsUtils::isCorsRequest).permitAll()
////                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                .requestMatchers("/api/v1/auth/**").permitAll()
//                .requestMatchers("/v2/api-docs/**").permitAll()
//                .requestMatchers("/swagger-ui/**").permitAll()
//                .requestMatchers("/swagger-resources/**").permitAll()
//                .requestMatchers("/swagger-ui.html").permitAll()
//                .requestMatchers("/webjars/**").permitAll()
////                .antMatchers(HttpMethod.OPTIONS, "/*").permitAll() 
//                .anyRequest()
//                .authenticated() .and()
//                .httpBasic()
//                .and()                
//                .exceptionHandling()
////                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        
//        return http.build();
//
//    }
//  	
//
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//
//	    }
//
//}

@Configuration
@EnableMethodSecurity
// (securedEnabled = true,
// jsr250Enabled = true,
// prePostEnabled = true) // by default
public class SecurityConfig { // extends WebSecurityConfigurerAdapter {
//  @Autowired
//  UserDetailsServiceImpl userDetailsService;
  

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(){
	    return  new JwtAuthenticationFilter();
	}

//  @Autowired
//  private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	CorsFilter customCorsFilter() {
	    CorsFilter filter = new CorsFilter();
	    return filter;
	}
//
//  @Bean
//  public DaoAuthenticationProvider authenticationProvider() {
//      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//       
//      authProvider.setUserDetailsService(userDetailsService);
//      authProvider.setPasswordEncoder(passwordEncoder());
//   
//      return authProvider;
//  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


 
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.csrf(csrf -> csrf.disable())
//        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
//        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .authorizeHttpRequests(auth -> 
//          auth.requestMatchers("/api/auth/**").permitAll()
//              .requestMatchers("/api/test/**").permitAll()
//              .anyRequest().authenticated()
//        );

		http.cors().and()
	.addFilterBefore(customCorsFilter(), SessionManagementFilter.class)
            .csrf(csrf -> csrf.disable())
            
            .authorizeRequests()
            .requestMatchers(CorsUtils::isCorsRequest).permitAll()
//            .requestMatchers(null).
//            .requestMatchers(HttpMethod.OPTIONS, "/*").permitAll()
            .requestMatchers("/api/v1/**").permitAll()
            .requestMatchers("/api/v1/auth/**").permitAll()
            .requestMatchers("/v2/api-docs/**").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/swagger-resources/**").permitAll()
            .requestMatchers("/swagger-ui.html").permitAll()
            .requestMatchers("swagger-ui.html").permitAll()
            .requestMatchers("/webjars/**").permitAll()
//            .antMatchers(HttpMethod.OPTIONS, "/*").permitAll() 
            .anyRequest()
            .authenticated().and()
            .httpBasic()
            .and()                
            .exceptionHandling()
//            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    
    	return http.build();
  	}
  
  
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication()
//              .withUser("root")
//              .password(passwordEncoder().encode("root"))
//              .authorities("ADMIN");
//  }
  	@Bean
  	public WebSecurityCustomizer webSecurityCustomizer() {
  		return (web) -> web.ignoring().requestMatchers("/images/**", 
				"/js/**", 
				"/webjars/**", 
				"/configuration/ui", 
				"/swagger-resources/**", 
				"/configuration/security",
				"/swagger-ui/**",
				"/swagger-ui.html"); 
  	}
  	
  	 @Bean
     public WebMvcConfigurer corConfigurer() {
         return new WebMvcConfigurer() {
             @Override
             public void addCorsMappings(CorsRegistry registry) {
                			    // This wildcard pattern matches any host from domain.com and url patterns like "https:microservice.division.domain.com/version1/some_endpoint"
                 registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns("*");
             }
         };
     }
}