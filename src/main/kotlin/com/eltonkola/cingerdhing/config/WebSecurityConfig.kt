package com.eltonkola.cingerdhing.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
                .antMatchers("/",
                        "/index",
                        "/login",
                        "/templates/**",
                        "/resources/**",
                        "/app-assets/**",
                        "/assets/**"
                        , "/css/**").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/").and()
                .logout().logoutUrl("/logout");

        http.csrf().disable();


    }

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        val user = User.withDefaultPasswordEncoder()
                .username("elton")
                .password("kola")
                .roles("USER", "ADMIN")
                .build()

        return InMemoryUserDetailsManager(user)
    }


}
