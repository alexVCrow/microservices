package ua.alexcrow.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import ua.alexcrow.carservice.controller.FeignErrorDecoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CarserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarserviceApplication.class, args);
	}

	@Bean
	public FeignErrorDecoder feignErrorDecoder(){
		return new FeignErrorDecoder();
	}

}
