package org.gatodev.hornito_system.msvc_supplier.repository;

import org.gatodev.hornito_system.msvc_supplier.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
