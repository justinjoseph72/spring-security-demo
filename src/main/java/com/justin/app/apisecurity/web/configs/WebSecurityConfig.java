package com.justin.app.apisecurity.web.configs;

import com.justin.app.apisecurity.web.filters.ApiRequestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private String[] checkLink = {"/check/register"};
    private String[] shareLinks = {"/shares"};

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(checkLink).hasAuthority("FE_USER")
                .antMatchers(shareLinks).hasAuthority("SHARES_USER")
                .antMatchers("/error").permitAll()
                .anyRequest().denyAll()
                .and().csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(getApiFilter(), BasicAuthenticationFilter.class);
    }


    private Filter getApiFilter() {
        return new ApiRequestFilter();
    }
}
