package org.gatodev.hornito_system.msvc_products.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float quantity;
    private BigDecimal price;
    private LocalDate expirationDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime registrationDate;

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StockLog> logs = new ArrayList<>();

    private Long supplierId;
}
