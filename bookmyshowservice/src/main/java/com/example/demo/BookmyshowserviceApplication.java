package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController
@EnableHystrix

public class BookmyshowserviceApplication {
	@Autowired
	RestTemplate restTemplate;
	@GetMapping("/book")
	@HystrixCommand(fallbackMethod = "bookMyShowFallBackMethod")
	public String bookmyshow() {
		String res1=restTemplate.getForObject("http://localhost:7171/notification/send",String.class);
		String res2=restTemplate.getForObject("http://localhost:8181/paytm/pay",String.class);
		return res1+" "+res2;
	}
	public String bookMyShowFallBackMethod() {
		return "internal server error";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BookmyshowserviceApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
