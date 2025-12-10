package org.gatodev.hornito_system.msvc_supplier.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supplier supplier;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    private LocalDateTime receivedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PurchaseSupplierDetail> details = new ArrayList<>();

    public BigDecimal getTotal() {
        return details.stream()
            .map(PurchaseSupplierDetail::getSubTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
