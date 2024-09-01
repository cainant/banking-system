package com.cainant.testebackendtgid.dto.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AccountTransactionData(
        @NotNull
        @Positive
        Float value
) {
}
