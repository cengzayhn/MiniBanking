package com.cengzayhn.mini_banking_api.model;

import com.cengzayhn.mini_banking_api.common.base.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Transaction extends BaseEntity {
    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    private TransactionStatus status;

    @ManyToOne
    private Account from;

    @ManyToOne
    private Account to;
}
