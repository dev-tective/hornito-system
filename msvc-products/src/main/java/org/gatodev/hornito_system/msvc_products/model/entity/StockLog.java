package org.gatodev.hornito_system.msvc_products.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.gatodev.hornito_system.msvc_products.model.enums.LogType;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, columnDefinition = "VARCHAR(20)")
    private LogType logType;

    private Float previousQuantity;
    private Float changeQuantity;
    private Float currentQuantity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime registrationDate;
}