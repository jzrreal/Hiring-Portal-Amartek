package com.hiringportal.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutConfiguration logoutConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                //Need authentication no matter what the roles
                .antMatchers("/api/dashboards", "/api/profiles", "/api/tokens").authenticated()
                .antMatchers(HttpMethod.GET,
                        "/api/job-posts",
                        "/api/job-posts/*",
                        "/api/education-histories/applicants/*",
                        "/api/education-histories",
                        "/api/job-functions",
                        "/api/skill-levels",
                        "/api/job-levels"
                ).authenticated()
                //Need authentication with role Candidate
                .antMatchers(HttpMethod.POST, "/api/applications/*", "/api/education-histories").hasAuthority("applicant")
                .antMatchers(HttpMethod.PUT, "/api/education-histories/*").hasAuthority("applicant")
                .antMatchers(HttpMethod.DELETE, "/api/applications/*", "/api/education-histories/*").hasAuthority("applicant")
                .antMatchers(HttpMethod.GET, "/api/applications", "/api/profiles/applicants").hasAuthority("applicant")
                //Need authentication with role HR
                .antMatchers(HttpMethod.POST, "/api/job-posts", "/api/online-tests/applications/*", "api/job-functions", "/api/skill-levels", "/api/question-levels", "/api/job-levels").hasAuthority("human resource")
                .antMatchers(HttpMethod.PUT, "/api/job-posts/*", "api/job-functions/*", "/api/skill-levels/*", "/api/question-levels/*", "/api/job-levels/*").hasAuthority("human resource")
                .antMatchers(HttpMethod.DELETE, "/api/job-posts/*", "api/job-functions/*", "/api/skill-levels/*", "/api/question-levels/*", "/api/job-levels/*").hasAuthority("human resource")
                .antMatchers(HttpMethod.GET, "/api/applications/job-post/*", "/api/applications/*/applicants", "api/job-functions/*", "/api/skill-levels/*", "/api/question-levels/*", "/api/job-levels/*").hasAuthority("human resource")
                .antMatchers("/api/application-status/**", "/api/roles/**", "api/test-parameters/**", "/api/applicants/**","/api/users/**").hasAuthority("human resource")
                //Need authentication with role Trainer
                .antMatchers(HttpMethod.POST, "/api/questions").hasAuthority("trainer")
                .antMatchers(HttpMethod.PUT, "/api/questions/*").hasAuthority("trainer")
                .antMatchers(HttpMethod.DELETE, "/api/questions/*").hasAuthority("trainer")
                .antMatchers(HttpMethod.GET, "/api/questions", "/api/questions/*").hasAuthority("trainer")
                //Trainer and HR
                .antMatchers(HttpMethod.GET, "/api/question-levels").hasAnyAuthority("trainer", "human resource")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .logout()
                .logoutUrl("/api/auth/logout")
                .addLogoutHandler(logoutConfiguration)
                .logoutSuccessHandler((request, response, authentication) ->
                        SecurityContextHolder.clearContext())
                .and()
                .cors();
        return http.build();
    }
//    .hasAnyAuthority("human resource")
}
