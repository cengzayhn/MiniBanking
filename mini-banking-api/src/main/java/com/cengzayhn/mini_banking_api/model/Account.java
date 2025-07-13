package com.cengzayhn.mini_banking_api.model;

import com.cengzayhn.mini_banking_api.common.base.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Account extends BaseEntity {
    @Column(name = "account_number", nullable = false, unique = true)
    private String number;

    @Column(name = "account_name", nullable = false, unique = true)
    private String name;

    @Column(name = "account_balance", nullable = false)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}
