package org.gatodev.hornito_system.msvc_operations;

import org.gatodev.hornito_system.msvc_operations.client.StockClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(types = { StockClient.class })
@SpringBootApplication
public class MsvcOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcOperationsApplication.class, args);
	}

}
