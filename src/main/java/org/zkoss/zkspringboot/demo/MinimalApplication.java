package org.zkoss.zkspringboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class MinimalApplication {
	public static void main(String[] args) {

		System.out.println("aaahhhhhh");
		SpringApplication.run(MinimalApplication.class);
	}

	@GetMapping("/test")
	public String test(){
		return "/zul/test";
	}

	@GetMapping("/minmal")
	public String minimal(){
		return "/zul/minmal";
	}
}
