package com.boot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
	
	
	@RequestMapping("/home")
	public String getHomepage()
	{
		return "homepage";
	}

}
