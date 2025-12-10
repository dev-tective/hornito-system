package org.gatodev.hornito_system.msvc_operations.service;

import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockOperationDto;
import org.gatodev.hornito_system.msvc_operations.model.entity.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> findAll();

    Sale findById(Long id);

    Sale save(StockOperationDto... stockOperations);

    void deleteById(Long id);
}
