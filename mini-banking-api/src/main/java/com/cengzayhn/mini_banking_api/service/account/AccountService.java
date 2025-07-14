package com.cengzayhn.mini_banking_api.service.account;

import com.cengzayhn.mini_banking_api.common.dto.FilterDTO;
import com.cengzayhn.mini_banking_api.dto.request.CreateAccountDTO;
import com.cengzayhn.mini_banking_api.model.Account;
import com.cengzayhn.mini_banking_api.model.User;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    Account getAccount(UUID accountId);
    Account saveAccount(CreateAccountDTO createAccountDTO, User owner);
    Account updateAccount(UUID accountId, Account updatedAccount);
    void deleteAccount(UUID accountId);
    Page<Account> getAllAccountsByUser(UUID userId, FilterDTO filterDTO);
    Page<Account> searchAccounts(UUID userId, FilterDTO filterDTO);
    Account updateBalance(UUID accountId, BigDecimal newBalance);
}