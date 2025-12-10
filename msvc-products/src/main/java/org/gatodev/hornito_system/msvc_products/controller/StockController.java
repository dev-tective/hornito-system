package org.gatodev.hornito_system.msvc_products.controller;

import java.util.List;

import org.gatodev.hornito_system.msvc_products.model.entity.Stock;
import org.gatodev.hornito_system.msvc_products.model.enums.LogType;
import org.gatodev.hornito_system.msvc_products.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Stock> save(@RequestBody Stock stock) {
        return ResponseEntity.ok(stockService.save(stock));
    }

    @PutMapping
    public ResponseEntity<Stock> update(@RequestBody Stock stock) {
        Stock currentStock = stockService.findById(stock.getId());
        currentStock.setPrice(stock.getPrice());
        currentStock.setExpirationDate(stock.getExpirationDate());
        return ResponseEntity.ok(stockService.save(currentStock));
    }

    private record StockUpdateQuantityRequest(Stock stock, LogType logType) {}

    @PutMapping("/quantity")
    public ResponseEntity<Stock> updateQuantity(@RequestBody StockUpdateQuantityRequest request) {
        return ResponseEntity.ok(stockService.updateStock(request.stock(), request.logType()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        stockService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
