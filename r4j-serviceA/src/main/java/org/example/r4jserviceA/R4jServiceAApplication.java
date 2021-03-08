package org.example.r4jserviceA;

import org.example.r4jserviceA.clients.ServiceBClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {ServiceBClient.class})
public class R4jServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(R4jServiceAApplication.class, args);
	}

}
