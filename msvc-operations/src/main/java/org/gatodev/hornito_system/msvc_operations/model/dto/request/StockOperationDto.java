package org.gatodev.hornito_system.msvc_operations.model.dto.request;

public record StockOperationDto(
    StockDto stock,
    Float quantityOperation
) {
}
