package com.cengzayhn.mini_banking_api.controller;

import com.cengzayhn.mini_banking_api.common.dto.FilterDTO;
import com.cengzayhn.mini_banking_api.common.response.ApiResponse;
import com.cengzayhn.mini_banking_api.dto.request.TransferRequestDTO;
import com.cengzayhn.mini_banking_api.model.Account;
import com.cengzayhn.mini_banking_api.model.Transaction;
import com.cengzayhn.mini_banking_api.model.User;
import com.cengzayhn.mini_banking_api.service.account.AccountService;
import com.cengzayhn.mini_banking_api.service.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Transaction Operations", description = "Endpoints for transferring money and viewing transaction history")
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @Operation(summary = "Transfer money between accounts")
    @PostMapping("/transfer")
    public ApiResponse<Transaction> transfer(@Valid @RequestBody TransferRequestDTO dto,
                                                @AuthenticationPrincipal User user) {
        Account fromAccount = accountService.getAccount(dto.getFromAccountId());
        if (!fromAccount.getOwner().getId().equals(user.getId())) {
            throw new AccessDeniedException("You are not authorized to transfer from this account.");
        }

        Transaction transaction = transactionService.transfer(dto);
        return ApiResponse.success(transaction);
    }

    @Operation(summary = "View transaction history of an account")
    @PostMapping("/account/{accountId}")
    public ApiResponse<Page<Transaction>> getTransactionHistory(@PathVariable UUID accountId,
                                                                   @AuthenticationPrincipal User user,
                                                                   @RequestBody FilterDTO filterDTO) {
        Account account = accountService.getAccount(accountId);
        if (!account.getOwner().getId().equals(user.getId())) {
            throw new AccessDeniedException("You are not authorized to view this account's transactions.");
        }

        Page<Transaction> transactions = transactionService.getTransactions(accountId, filterDTO);
        return ApiResponse.success(transactions);
    }
}
