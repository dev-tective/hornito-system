package org.gatodev.hornito_system.msvc_operations.model.dto.request;

public record ShrinkageRequestDto(
        StockOperationDto operation,
        String note) {
}
