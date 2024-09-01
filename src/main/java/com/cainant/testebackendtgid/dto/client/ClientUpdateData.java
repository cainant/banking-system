package com.cainant.testebackendtgid.dto.client;

import jakarta.validation.constraints.NotBlank;

public record ClientUpdateData(
        @NotBlank
        String name
) {
}
