package org.gatodev.hornito_system.msvc_supplier.client;

import org.gatodev.hornito_system.msvc_supplier.model.dto.request.StockDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("http://localhost:8082/stocks")
public interface StockClient {
    @PostExchange
    StockDto addStock(@RequestBody StockDto stockDto);
}
