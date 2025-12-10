package org.gatodev.hornito_system.msvc_operations.service;

import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockOperationDto;
import org.gatodev.hornito_system.msvc_operations.model.entity.Shrinkage;

import java.util.List;

public interface ShrinkageService {
    List<Shrinkage> findAll();

    Shrinkage findById(Long id);

    Shrinkage save(StockOperationDto operation, String note);

    Shrinkage save(Long stockId, Float quantity, String note);

    void deleteById(Long id);
}
