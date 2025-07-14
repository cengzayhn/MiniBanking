package com.cengzayhn.mini_banking_api.model;

import com.cengzayhn.mini_banking_api.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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
