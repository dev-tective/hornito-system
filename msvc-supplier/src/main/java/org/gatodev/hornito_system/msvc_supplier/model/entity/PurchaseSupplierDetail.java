package org.gatodev.hornito_system.msvc_supplier.model.entity;

import java.math.BigDecimal;

import org.gatodev.hornito_system.msvc_supplier.model.dto.request.ProductDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseSupplierDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private Long productId;

    @Transient
    private ProductDto product;

    @Column(nullable = false)
    private Float quantity;

    @Column(nullable = false)
    private BigDecimal priceUnit;

    @Column(nullable = false)
    private LocalDate expirationDate;

    public BigDecimal getSubTotal() {
        return priceUnit.multiply(new BigDecimal(quantity));
    }
}
