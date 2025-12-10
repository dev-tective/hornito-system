package org.gatodev.hornito_system.msvc_supplier;

import org.gatodev.hornito_system.msvc_supplier.client.ProductClient;
import org.gatodev.hornito_system.msvc_supplier.client.StockClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(types = { ProductClient.class, StockClient.class })
@SpringBootApplication
public class MsvcSupplierApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcSupplierApplication.class, args);
	}

}
