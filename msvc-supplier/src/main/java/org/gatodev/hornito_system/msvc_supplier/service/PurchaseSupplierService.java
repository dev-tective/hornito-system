package org.gatodev.hornito_system.msvc_supplier.service;

import java.time.LocalDateTime;
import java.util.List;

import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplier;
import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplierDetail;

public interface PurchaseSupplierService {
    PurchaseSupplier save(PurchaseSupplier purchaseSupplier);

    PurchaseSupplier updateProducts(Long id, List<PurchaseSupplierDetail> details);

    PurchaseSupplier updateDeliveryDate(Long id, LocalDateTime deliveryDate);

    PurchaseSupplier receivedPurchaseSupplier(Long id);

    List<PurchaseSupplier> findAll();

    PurchaseSupplier findById(Long id);

    void deleteById(Long id);
}
