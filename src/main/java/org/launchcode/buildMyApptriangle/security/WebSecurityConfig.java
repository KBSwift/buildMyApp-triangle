package org.launchcode.buildMyApptriangle.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*  If my security is getting annoying for your bug testing,
    replace @Order(Ordered.LOWEST_PRECEDENCE) with @Order(Ordered.HIGHEST_PRECEDENCE) below
    It will set all site security to require no authentication
    TODO: Re-enable csrf in lowest precedence chain before release! */

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityFilterChainTwo(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**").permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    @Bean
    @Order(1)
    public SecurityFilterChain registerChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/register")
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/register").anonymous()
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    @Order(3)
    public SecurityFilterChain basicAuthenticationChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/contracts/**", "/customers/**", "/employees/**")
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/**").authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain adminAuthenticationChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/contracts/add", "/contracts/delete", "/contracts/view/{id}/update", "/customers/add", "/customers/delete", "/customers/view/{id}/update", "/employees/add", "/employees/delete", "/employees/view/{id}/update")
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/**").hasRole("ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
