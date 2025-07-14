package com.cengzayhn.mini_banking_api.repository;

import com.cengzayhn.mini_banking_api.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("""
    SELECT t FROM Transaction t
    WHERE t.from.id = :accountId OR t.to.id = :accountId
    ORDER BY t.createdAt DESC
    """)
    Page<Transaction> findTransactionsByAccount(UUID accountId, Pageable pageable);
}
