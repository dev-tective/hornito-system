package org.gatodev.hornito_system.msvc_operations.service.impl;

import org.gatodev.hornito_system.msvc_operations.client.StockClient;
import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockDto;
import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockOperationDto;
import org.gatodev.hornito_system.msvc_operations.model.entity.Sale;
import org.gatodev.hornito_system.msvc_operations.model.entity.SaleDetail;
import org.gatodev.hornito_system.msvc_operations.repository.SaleRepository;
import org.gatodev.hornito_system.msvc_operations.service.SaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final StockClient stockClient;

    @Override
    @Transactional(readOnly = true)
    public List<Sale> findAll() {
        return repository.findAll().stream()
            .map(sale -> {
                sale.getSaleDetails().forEach(this::setSaleDetail);
                return sale;
            })
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Sale findById(Long id) {
        return repository.findById(id)
            .map(sale -> {
                sale.getSaleDetails().forEach(this::setSaleDetail);
                return sale;
            })
            .orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    @Transactional
    public Sale save(StockOperationDto... stockOperations) {
        Sale sale = new Sale();
        
        Arrays.stream(stockOperations)
            .forEach(operation -> {
                StockDto currentStock = stockClient.getStockById(operation.stock().id());
                Float newQuantity = currentStock.quantity() - operation.quantityOperation();
                
                // Validar primero
                if (newQuantity < 0) {
                    throw new RuntimeException("Not enough stock for product: " + currentStock.id());
                }
                
                // Actualizar stock
                StockDto updatedStock = new StockDto(
                    currentStock.id(),
                    currentStock.product(),
                    newQuantity,
                    currentStock.price()
                );
                
                Map<String, Object> stock = new HashMap<>();
                stock.put("stock", updatedStock);
                stock.put("logType", "VENTA");
                stockClient.updateStockQuantity(stock);
                
                // Guardar detalle con la cantidad VENDIDA
                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setStockId(currentStock.id());
                saleDetail.setQuantity(operation.quantityOperation());
                saleDetail.setPrice(currentStock.price());
                sale.getSaleDetails().add(saleDetail);
            });
        
        return repository.save(sale);
    }

    private SaleDetail setSaleDetail(SaleDetail saleDetail) {
        StockDto stockDto = stockClient.getStockById(saleDetail.getStockId());
        Number idNumber = (Number) stockDto.product().get("id");
        saleDetail.setProductId(idNumber.longValue());
        saleDetail.setProductName((String) stockDto.product().get("name"));
        return saleDetail;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
