package org.gatodev.hornito_system.msvc_products.service;

import org.gatodev.hornito_system.msvc_products.model.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Integer id);

    Category save(Category category);

    void deleteById(Integer id);
}
