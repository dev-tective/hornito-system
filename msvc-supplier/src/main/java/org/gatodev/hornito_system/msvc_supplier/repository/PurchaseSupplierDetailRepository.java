package org.gatodev.hornito_system.msvc_supplier.repository;

import org.gatodev.hornito_system.msvc_supplier.model.entity.PurchaseSupplierDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseSupplierDetailRepository extends JpaRepository<PurchaseSupplierDetail, Long> {
}
