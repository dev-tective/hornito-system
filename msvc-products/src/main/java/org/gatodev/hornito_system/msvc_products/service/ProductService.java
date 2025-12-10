package org.gatodev.hornito_system.msvc_products.service;

import org.gatodev.hornito_system.msvc_products.model.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);
}
