package com.cengzayhn.mini_banking_api.service.transaction;

import com.cengzayhn.mini_banking_api.common.dto.FilterDTO;
import com.cengzayhn.mini_banking_api.dto.request.TransferRequestDTO;
import com.cengzayhn.mini_banking_api.model.Transaction;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TransactionService {
    Transaction transfer(TransferRequestDTO transferRequestDTO);
    Page<Transaction> getTransactions(UUID accountId, FilterDTO filterDTO);
}
