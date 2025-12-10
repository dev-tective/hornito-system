package org.gatodev.hornito_system.msvc_products.repository;

import org.gatodev.hornito_system.msvc_products.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
