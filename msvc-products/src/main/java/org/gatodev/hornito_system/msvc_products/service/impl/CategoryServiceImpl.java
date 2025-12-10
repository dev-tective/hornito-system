package org.gatodev.hornito_system.msvc_products.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.gatodev.hornito_system.msvc_products.model.entity.Category;
import org.gatodev.hornito_system.msvc_products.repository.CategoryRepository;
import org.gatodev.hornito_system.msvc_products.service.CategoryService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Transactional
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
