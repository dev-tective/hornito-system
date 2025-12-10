package org.gatodev.hornito_system.msvc_supplier.service;

import java.util.List;

import org.gatodev.hornito_system.msvc_supplier.model.entity.Supplier;

public interface SupplierService {
    public List<Supplier> findAll();

    public Supplier findById(Long id);

    public Supplier save(Supplier supplier);

    public void deleteById(Long id);
}
