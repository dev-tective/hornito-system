package org.gatodev.hornito_system.msvc_operations.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stockId;

    @Transient
    private Long productId;

    @Transient
    private String productName;

    private Float quantity;

    private BigDecimal price;

    public BigDecimal getSubtotal() {
        return price.multiply(new BigDecimal(quantity));
    }
}
