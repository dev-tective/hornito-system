package org.gatodev.hornito_system.msvc_operations.controller;

import lombok.RequiredArgsConstructor;
import org.gatodev.hornito_system.msvc_operations.model.dto.request.StockOperationDto;
import org.gatodev.hornito_system.msvc_operations.model.entity.Sale;
import org.gatodev.hornito_system.msvc_operations.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public List<Sale> findAll() {
        return saleService.findAll();
    }

    @GetMapping("/{id}")
    public Sale findById(@PathVariable Long id) {
        return saleService.findById(id);
    }

    @PostMapping
    public Sale save(@RequestBody List<StockOperationDto> stockOperations) {
        return saleService.save(stockOperations.toArray(new StockOperationDto[0]));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        saleService.deleteById(id);
    }
}
