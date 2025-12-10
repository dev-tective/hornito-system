package org.gatodev.hornito_system.msvc_operations.repository;

import org.gatodev.hornito_system.msvc_operations.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
