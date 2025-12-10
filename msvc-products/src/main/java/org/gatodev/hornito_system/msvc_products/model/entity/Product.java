package org.gatodev.hornito_system.msvc_products.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import org.gatodev.hornito_system.msvc_products.model.enums.ProductPresentation;
import org.gatodev.hornito_system.msvc_products.model.enums.ProductType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal referencePrice;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Enumerated(EnumType.STRING)
    private ProductPresentation presentation;

    @ManyToOne
    private Category category;
}
