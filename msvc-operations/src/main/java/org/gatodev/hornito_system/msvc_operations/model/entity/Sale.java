package org.gatodev.hornito_system.msvc_operations.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime saleDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleDetail> saleDetails = new ArrayList<>();

    public BigDecimal getTotal() {
        return saleDetails.stream()
            .map(SaleDetail::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
