package com.cainant.testebackendtgid.dto.enterprise;

import jakarta.validation.constraints.NotBlank;

public record EnterpriseUpdateData(
        @NotBlank
        String webhookURL
) {
}
