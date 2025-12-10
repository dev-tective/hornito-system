package org.gatodev.hornito_system.msvc_operations.client;

import java.util.Map;

import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange("http://localhost:8082/stocks")
public interface StockClient {

    @GetExchange("/{id}")
    StockDto getStockById(@PathVariable Long id);


    @PutExchange("/quantity")
    StockDto updateStockQuantity(@RequestBody Map<String, Object> stock);
}
