package com.administrador.Administrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableFeignClients
@EnableMongoRepositories
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AdministradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministradorApplication.class, args);
	}

}
