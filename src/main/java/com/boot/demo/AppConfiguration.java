package com.boot.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@Configuration
public class AppConfiguration {

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resourceDetails,OAuth2ClientContext clientContext)
	{
		return new OAuth2RestTemplate(resourceDetails,clientContext);
	}
	
}
