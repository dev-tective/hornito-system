package org.gatodev.hornito_system.msvc_supplier.model.dto.request;

import java.math.BigDecimal;
import java.util.Map;

public record ProductDto(
    Long id,
    String name,
    BigDecimal price,
    String presentation,
    String type,
    Map<String, String> category
) {

}
