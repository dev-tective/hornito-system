package org.gatodev.hornito_system.msvc_supplier.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StockDto(
    ProductDto product, 
    Float quantity,
    BigDecimal price,
    LocalDate expirationDate,
    Long supplierId
    ) {
}