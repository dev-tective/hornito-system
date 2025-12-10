package org.gatodev.hornito_system.msvc_products.repository;

import org.gatodev.hornito_system.msvc_products.model.entity.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockLogRepository extends JpaRepository<StockLog, Long> {
}
