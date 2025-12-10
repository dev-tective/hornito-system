package org.gatodev.hornito_system.msvc_supplier.client;

import org.gatodev.hornito_system.msvc_supplier.model.dto.request.ProductDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("http://localhost:8082/products")
public interface ProductClient {

    @GetExchange
    List<ProductDto> getAllProducts();

    @GetExchange("/{id}")
    ProductDto getProductById(@PathVariable Long id);

    @PostExchange
    ProductDto createProduct(@RequestBody ProductDto productDto);
}
