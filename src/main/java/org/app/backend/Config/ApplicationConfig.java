package org.app.backend.Config;

import lombok.RequiredArgsConstructor;
import org.app.backend.User.repo.IntRepUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final IntRepUser rep;
    @Bean
    public UserDetailsService userDetailsService(){
    return username -> {
        return rep.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("usernotfound"));
    };
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authentication =new DaoAuthenticationProvider();
        authentication.setUserDetailsService(userDetailsService());
        authentication.setPasswordEncoder(passwordEncoder());
        return authentication;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
}
