package com.boot.demo;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
public class AppController extends WebSecurityConfigurerAdapter  {
	
	
	@RequestMapping("/home")
	public String getHomepage()
	{
		return "homepage";
	}
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/login**")
            .permitAll()
          .anyRequest()
            .authenticated();
    }

}
