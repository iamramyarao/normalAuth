package com.example.samplelogin.configuration;

import com.example.samplelogin.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Ramya Rao on 08-11-2021 18:08
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
               .anyRequest()//Make sure any requests coming in are authenticated
                .authenticated()//
                .and()
                .formLogin()//Handle form login
                .loginPage("/login")//THis is the login URL
                .permitAll()//Give permissions to all for logging in
                .failureUrl("/login?error=true")//Incase failure of login go to failure URL
                .defaultSuccessUrl("/home")//Incase of login success go to /
                .usernameParameter("email")//This is the username parameter
                .passwordParameter("password")//THis is password parameter
                .and()
                .logout()//handle logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//Logout URL
                .logoutSuccessUrl("/login")//Incase logout success, redirect to login
                .invalidateHttpSession(true)//close and delete cookies
                .deleteCookies("JSESSIONID")//This param is in browser
                .and()
                .exceptionHandling()//handle exceptions
                .and()
                .csrf()//disable csrf
                .disable();

    }

    /**
     * CHeck password in encrypted format
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Based on the information given by us, the system retrieves the information and create a custom user object,
     * build and pass it.
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService);//We need to create this customUserDetailService service.

    }
}
