package com.cengzayhn.mini_banking_api.service.transaction;

import com.cengzayhn.mini_banking_api.common.dto.FilterDTO;
import com.cengzayhn.mini_banking_api.common.utils.PaginationUtils;
import com.cengzayhn.mini_banking_api.dto.request.TransferRequestDTO;
import com.cengzayhn.mini_banking_api.model.Account;
import com.cengzayhn.mini_banking_api.model.Transaction;
import com.cengzayhn.mini_banking_api.model.TransactionStatus;
import com.cengzayhn.mini_banking_api.repository.TransactionRepository;
import com.cengzayhn.mini_banking_api.service.account.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction transfer(TransferRequestDTO dto) {
        validateTransferRequest(dto);

        Account fromAccount = accountService.getAccount(dto.getFromAccountId());
        Account toAccount = accountService.getAccount(dto.getToAccountId());

        if (!hasSufficientBalance(fromAccount, dto.getAmount())) {
            return saveTransaction(fromAccount, toAccount, dto.getAmount(), TransactionStatus.FAILED);
        }

        performTransfer(fromAccount, toAccount, dto.getAmount());

        return saveTransaction(fromAccount, toAccount, dto.getAmount(), TransactionStatus.SUCCESS);
    }

    @Override
    public Page<Transaction> getTransactions(UUID accountId, FilterDTO filterDTO) {
        Pageable pageable = PaginationUtils.buildPagination(filterDTO);

        accountService.getAccount(accountId);

        return transactionRepository.findTransactionsByAccount(accountId, pageable);
    }

    private void validateTransferRequest(TransferRequestDTO dto) {
        if (dto.isSelfTransfer()) {
            throw new IllegalArgumentException("Cannot transfer to the same account.");
        }
    }

    private boolean hasSufficientBalance(Account account, BigDecimal amount) {
        return account.getBalance().compareTo(amount) >= 0;
    }

    private void performTransfer(Account from, Account to, BigDecimal amount) {
        validateTransferAmount(amount);

        BigDecimal fromNewBalance = from.getBalance().subtract(amount);
        BigDecimal toNewBalance = to.getBalance().add(amount);

        accountService.updateBalance(from.getId(), fromNewBalance);
        accountService.updateBalance(to.getId(), toNewBalance);
    }

    private void validateTransferAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be a positive, non-null value.");
        }
    }

    private Transaction saveTransaction(Account from, Account to, BigDecimal amount, TransactionStatus status) {
        Transaction transaction = new Transaction();
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setAmount(amount);
        transaction.setStatus(status);
        return transactionRepository.save(transaction);
    }
}
