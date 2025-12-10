package org.gatodev.hornito_system.msvc_products.service;

import org.gatodev.hornito_system.msvc_products.model.entity.Stock;
import org.gatodev.hornito_system.msvc_products.model.enums.LogType;

import java.util.List;

public interface StockService {
    List<Stock> findAll();

    Stock findById(Long id);

    Stock save(Stock stock);

    Stock updateStock(Stock stock, LogType logType);

    void deleteById(Long id);
}
