package org.gatodev.hornito_system.msvc_products.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.gatodev.hornito_system.msvc_products.model.entity.Product;
import org.gatodev.hornito_system.msvc_products.model.entity.Category;
import org.gatodev.hornito_system.msvc_products.repository.ProductRepository;
import org.gatodev.hornito_system.msvc_products.service.CategoryService;
import org.gatodev.hornito_system.msvc_products.service.ProductService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    @Override
    public Product save(Product product) {
        Category category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
