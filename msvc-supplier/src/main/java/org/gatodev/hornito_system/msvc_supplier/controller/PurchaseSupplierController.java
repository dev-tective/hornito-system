package org.gatodev.hornito_system.msvc_supplier.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplier;
import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplierDetail;
import org.gatodev.hornito_system.msvc_supplier.service.PurchaseSupplierService;
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
@RequestMapping("/purchases-suppliers")
@RequiredArgsConstructor
public class PurchaseSupplierController {
    
    private final PurchaseSupplierService purchaseSupplierService;

    @PostMapping
    public PurchaseSupplier save(@RequestBody PurchaseSupplier purchaseSupplier) {
        return purchaseSupplierService.save(purchaseSupplier);
    }

    @PutMapping("/{id}/products")
    public PurchaseSupplier updateProducts(@PathVariable Long id, @RequestBody List<PurchaseSupplierDetail> details) {
        return purchaseSupplierService.updateProducts(id, details);
    }

    @PutMapping("/{id}/delivery-date")
    public PurchaseSupplier updateDeliveryDate(@PathVariable Long id, @RequestBody LocalDateTime deliveryDate) {
        return purchaseSupplierService.updateDeliveryDate(id, deliveryDate);
    }

    @PutMapping("/{id}/received")
    public PurchaseSupplier receivedPurchaseSupplier(@PathVariable Long id) {
        return purchaseSupplierService.receivedPurchaseSupplier(id);
    }

    @GetMapping
    public List<PurchaseSupplier> findAll() {
        return purchaseSupplierService.findAll();
    }

    @GetMapping("/{id}")
    public PurchaseSupplier findById(@PathVariable Long id) {
        return purchaseSupplierService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        purchaseSupplierService.deleteById(id);
    }
}
