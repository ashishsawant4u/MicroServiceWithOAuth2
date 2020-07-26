package com.boot.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
public class AppController extends WebSecurityConfigurerAdapter  {
	
	
	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;
	
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
	
	@RequestMapping("/home")
	public String getHomepage()
	{
		return "homepage";
	}
	
	
	@RequestMapping("/products")
	public String getProducts(Model model)
	{	
		System.out.println("TOKEN >> "+oAuth2ClientContext.getAccessToken());
		
		ResponseEntity<ArrayList<ProductData>> resp = oAuth2RestTemplate.exchange("http://localhost:8014/microserviceX/getProducts", HttpMethod.GET,null,new ParameterizedTypeReference<ArrayList<ProductData>>(){});
		
		System.out.println("Resp data >> "+resp.getBody().size());
		
		model.addAttribute("products", resp.getBody());
		
		return "productsList";
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
