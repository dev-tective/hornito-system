package org.gatodev.hornito_system.msvc_operations.service.impl;

import org.gatodev.hornito_system.msvc_operations.client.StockClient;
import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockDto;
import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockOperationDto;
import org.gatodev.hornito_system.msvc_operations.model.entity.Shrinkage;
import org.gatodev.hornito_system.msvc_operations.repository.ShrinkageRepository;
import org.gatodev.hornito_system.msvc_operations.service.ShrinkageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShrinkageServiceImpl implements ShrinkageService {

    private final ShrinkageRepository repository;
    private final StockClient stockClient;

    @Override
    @Transactional(readOnly = true)
    public List<Shrinkage> findAll() {
        return repository.findAll().stream()
            .map(this::setShrinkageDetail)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Shrinkage findById(Long id) {
        return repository.findById(id)
            .map(this::setShrinkageDetail)
            .orElseThrow(() -> new RuntimeException("Shrinkage not found"));
    }

    @Override
    @Transactional
    public Shrinkage save(StockOperationDto operation, String note) {
        StockDto currentStock = stockClient.getStockById(operation.stock().id());
        Float newQuantity = currentStock.quantity() - operation.quantityOperation();
        
        // Validar que hay suficiente stock
        if (newQuantity < 0) {
            throw new RuntimeException("Not enough stock for product: " + currentStock.id());
        }
        
        // Validar que la cantidad de merma es positiva
        if (operation.quantityOperation() <= 0) {
            throw new RuntimeException("Shrinkage quantity must be positive");
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
        stock.put("logType", "MERMA");
        stockClient.updateStockQuantity(stock);
        
        // Crear registro de merma
        Shrinkage shrinkage = new Shrinkage();
        shrinkage.setStockId(currentStock.id());
        shrinkage.setQuantity(operation.quantityOperation());
        shrinkage.setNote(note);
        
        return repository.save(shrinkage);
    }

    @Override
    @Transactional
    public Shrinkage save(Long stockId, Float quantity, String note) {
        StockOperationDto operation = new StockOperationDto(
            new StockDto(stockId, null, null, null),
            quantity
        );
        return save(operation, note);
    }

    private Shrinkage setShrinkageDetail(Shrinkage shrinkage) {
        StockDto stockDto = stockClient.getStockById(shrinkage.getStockId());
        Number idNumber = (Number) stockDto.product().get("id");
        shrinkage.setProductId(idNumber.longValue());
        shrinkage.setProductName((String) stockDto.product().get("name"));
        return shrinkage;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
