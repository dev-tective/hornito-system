package org.gatodev.hornito_system.msvc_operations.model.dto.request;

import java.math.BigDecimal;
import java.util.Map;

public record StockDto(
    Long id,
    Map<String, Object> product,
    Float quantity,
    BigDecimal price
) {
}

