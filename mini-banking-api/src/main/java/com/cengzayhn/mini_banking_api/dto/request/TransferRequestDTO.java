package com.cengzayhn.mini_banking_api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransferRequestDTO {
    @NotNull(message = "Source account ID (fromAccountId) cannot be null.")
    private UUID fromAccountId;

    @NotNull(message = "Destination account ID (toAccountId) cannot be null.")
    private UUID toAccountId;

    @NotNull(message = "Transfer amount cannot be null.")
    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than 0.")
    private BigDecimal amount;

    public boolean isSelfTransfer() {
        return fromAccountId != null && fromAccountId.equals(toAccountId);
    }
}
