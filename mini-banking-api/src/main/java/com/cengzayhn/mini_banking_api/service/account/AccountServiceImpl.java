package com.cengzayhn.mini_banking_api.service.account;

import com.cengzayhn.mini_banking_api.common.dto.FilterDTO;
import com.cengzayhn.mini_banking_api.common.exception.NotFoundException;
import com.cengzayhn.mini_banking_api.common.utils.PaginationUtils;
import com.cengzayhn.mini_banking_api.dto.request.CreateAccountDTO;
import com.cengzayhn.mini_banking_api.model.Account;
import com.cengzayhn.mini_banking_api.model.User;
import com.cengzayhn.mini_banking_api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account saveAccount(CreateAccountDTO createAccountDTO, User owner) {
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setOwner(owner);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setNumber(createAccountDTO.getNumber());
        account.setBalance(createAccountDTO.getBalance());
        account.setName(createAccountDTO.getName());

        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(UUID accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Page<Account> getAllAccountsByUser(UUID userId, FilterDTO filterDTO) {
        Pageable pageable = PaginationUtils.buildPagination(filterDTO);

        return accountRepository.findByOwnerId(userId, pageable);
    }

    @Override
    public Page<Account> searchAccounts(UUID userId, FilterDTO filterDTO) {
        Pageable pageable = PaginationUtils.buildPagination(filterDTO);

        return accountRepository.findByOwnerIdAndNameContainingIgnoreCase(
                userId, filterDTO.getSearchText(), pageable);
    }

    @Override
    public Account updateAccount(UUID accountId, Account updatedAccount) {
        Account account = getAccount(accountId);
        account.setName(updatedAccount.getName());
        account.setNumber(updatedAccount.getNumber());
        account.setUpdatedAt(LocalDateTime.now());

        return accountRepository.save(account);
    }

    @Override
    public Account updateBalance(UUID accountId, BigDecimal newBalance) {
        Account account = getAccount(accountId);
        account.setBalance(newBalance);

        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(UUID accountId) {
        Account account = getAccount(accountId);

        accountRepository.delete(account);
    }

}
