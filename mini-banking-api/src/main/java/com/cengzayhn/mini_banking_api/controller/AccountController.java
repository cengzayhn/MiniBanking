package com.cengzayhn.mini_banking_api.controller;

import com.cengzayhn.mini_banking_api.common.dto.FilterDTO;
import com.cengzayhn.mini_banking_api.common.response.ApiResponse;
import com.cengzayhn.mini_banking_api.dto.request.CreateAccountDTO;
import com.cengzayhn.mini_banking_api.model.Account;
import com.cengzayhn.mini_banking_api.model.User;
import com.cengzayhn.mini_banking_api.service.account.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Tag(name = "Account Operations", description = "Endpoints for creating, viewing, updating and deleting accounts")
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "Create a new account")
    @PostMapping
    public ApiResponse<Account> createAccount(@Valid @RequestBody CreateAccountDTO accountDTO, @AuthenticationPrincipal User authenticatedUser) {
        return ApiResponse.success(accountService.saveAccount(accountDTO, authenticatedUser));
    }

    @Operation(summary = "Get account details by ID")
    @GetMapping("/{id}")
    public ApiResponse<Account> getAccount(@PathVariable UUID id) {
        return ApiResponse.success(accountService.getAccount(id));
    }

    @Operation(summary = "Update account balance")
    @PutMapping("/{id}/balance")
    public ApiResponse<Account> updateBalance(@PathVariable UUID id,
                                                 @RequestParam BigDecimal balance) {
        return ApiResponse.success(accountService.updateBalance(id, balance));
    }

    @Operation(summary = "Delete an account")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ApiResponse.noContent();
    }

    @Operation(summary = "Search accounts by number or name")
    @PostMapping("/{id}")
    public ApiResponse<Page<Account>> searchAccounts(@PathVariable UUID id, @RequestBody FilterDTO filterDTO) {
        return ApiResponse.success(accountService.searchAccounts(id, filterDTO));
    }
}
