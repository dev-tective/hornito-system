package org.gatodev.hornito_system.msvc_products.service.impl;

import java.util.List;

import org.gatodev.hornito_system.msvc_products.model.entity.Product;
import org.gatodev.hornito_system.msvc_products.model.entity.Stock;
import org.gatodev.hornito_system.msvc_products.model.entity.StockLog;
import org.gatodev.hornito_system.msvc_products.model.enums.LogType;
import org.gatodev.hornito_system.msvc_products.repository.StockRepository;
import org.gatodev.hornito_system.msvc_products.service.ProductService;
import org.gatodev.hornito_system.msvc_products.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final ProductService productService;
    private final StockRepository stockRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Stock findById(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    @Transactional
    @Override
    public Stock save(Stock stock) {
        Product product = productService.findById(stock.getProduct().getId());
        stock.setProduct(product);
        return stockRepository.save(stock);
    }

    @Transactional
    @Override
    public Stock updateStock(Stock newStock, LogType logType) {
        Stock stock = stockRepository.findById(newStock.getId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        
        StockLog stockLog = new StockLog();
        stockLog.setLogType(logType);
        stockLog.setPreviousQuantity(stock.getQuantity());
        stockLog.setChangeQuantity(newStock.getQuantity() - stock.getQuantity());
        stockLog.setCurrentQuantity(newStock.getQuantity());

        stock.setQuantity(newStock.getQuantity());
        stock.getLogs().add(stockLog);
        return stockRepository.save(stock);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }
}
