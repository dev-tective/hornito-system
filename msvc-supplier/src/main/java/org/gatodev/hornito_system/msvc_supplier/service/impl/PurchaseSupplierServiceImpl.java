package org.gatodev.hornito_system.msvc_supplier.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.gatodev.hornito_system.msvc_supplier.client.ProductClient;
import org.gatodev.hornito_system.msvc_supplier.client.StockClient;
import org.gatodev.hornito_system.msvc_supplier.model.dto.request.StockDto;
import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplier;
import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplierDetail;
import org.gatodev.hornito_system.msvc_supplier.repository.PurchaseSupplierRepository;
import org.gatodev.hornito_system.msvc_supplier.service.PurchaseSupplierService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseSupplierServiceImpl implements PurchaseSupplierService {
    private final PurchaseSupplierRepository purchaseSupplierRepository;
    private final ProductClient productClient;
    private final StockClient stockClient;

    @Override
    public PurchaseSupplier save(PurchaseSupplier purchaseSupplier) {
        return purchaseSupplierRepository.save(purchaseSupplier);
    }

    @Override
    public PurchaseSupplier updateProducts(Long id, List<PurchaseSupplierDetail> details) {
        return purchaseSupplierRepository.findById(id)
            .map(purchaseSupplier -> {
                purchaseSupplier.setDetails(details);
                return purchaseSupplierRepository.save(purchaseSupplier);
            })
            .orElseThrow(() -> new RuntimeException("PurchaseSupplier not found"));
    }

    @Override
    public PurchaseSupplier updateDeliveryDate(Long id, LocalDateTime deliveryDate) {
        return purchaseSupplierRepository.findById(id)
            .map(purchaseSupplier -> {
                purchaseSupplier.setDeliveryDate(deliveryDate);
                return purchaseSupplierRepository.save(purchaseSupplier);
            })
            .orElseThrow(() -> new RuntimeException("PurchaseSupplier not found"));
    }

    @Override
    public List<PurchaseSupplier> findAll() {
        return purchaseSupplierRepository.findAll()
            .stream()
            .map(this::setPurchaseSupplierDetails)
            .toList();
    }

    @Override
    public PurchaseSupplier findById(Long id) {
        return purchaseSupplierRepository.findById(id)
            .map(this::setPurchaseSupplierDetails)
            .orElseThrow(() -> new RuntimeException("PurchaseSupplier not found"));
    }

    private PurchaseSupplier setPurchaseSupplierDetails(PurchaseSupplier purchaseSupplier) {
        purchaseSupplier.getDetails().forEach(detail -> {
            detail.setProduct(productClient.getProductById(detail.getProductId()));
        });
        return purchaseSupplier;
    }

    @Override
    public void deleteById(Long id) {
        purchaseSupplierRepository.deleteById(id);
    }

    @Override
    public PurchaseSupplier receivedPurchaseSupplier(Long id) {
        return purchaseSupplierRepository.findById(id)
            .map(purchaseSupplier -> {
                purchaseSupplier.setReceivedDate(LocalDateTime.now());
                purchaseSupplier.getDetails().forEach(detail -> {
                    StockDto stock = new StockDto(
                        productClient.getProductById(detail.getProductId()),
                        detail.getQuantity(),
                        detail.getPriceUnit(),
                        detail.getExpirationDate(),
                        purchaseSupplier.getSupplier().getId()
                    );
                    stockClient.addStock(stock);
                });
                return purchaseSupplierRepository.save(purchaseSupplier);
            })
            .orElseThrow(() -> new RuntimeException("PurchaseSupplier not found"));
    }
}
