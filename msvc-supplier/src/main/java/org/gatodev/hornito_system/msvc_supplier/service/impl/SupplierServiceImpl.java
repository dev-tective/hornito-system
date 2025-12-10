package org.gatodev.hornito_system.msvc_supplier.service.impl;

import java.util.Arrays;
import java.util.List;

import org.gatodev.hornito_system.msvc_supplier.client.ProductClient;
import org.gatodev.hornito_system.msvc_supplier.model.dto.request.ProductDto;
import org.gatodev.hornito_system.msvc_supplier.model.entity.Supplier;
import org.gatodev.hornito_system.msvc_supplier.repository.SupplierRepository;
import org.gatodev.hornito_system.msvc_supplier.service.SupplierService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ProductClient productClient;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll()
            .stream()
            .map(this::setProducts)
            .toList();
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id)
            .map(this::setProducts)
            .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    private Supplier setProducts(Supplier supplier) {
        List<ProductDto> products = Arrays.stream(supplier.getProductsIds())
            .map(productClient::getProductById)
            .toList();
        supplier.setProducts(products);
        return supplier;
    }

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}
