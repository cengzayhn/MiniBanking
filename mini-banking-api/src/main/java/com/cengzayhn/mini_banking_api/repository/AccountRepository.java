package com.cengzayhn.mini_banking_api.repository;

import com.cengzayhn.mini_banking_api.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Page<Account> findByOwnerIdAndNameContainingIgnoreCase(
            UUID ownerId, String namePart,
            Pageable pageable
    );

    Page<Account> findByOwnerId(UUID ownerId, Pageable pageable);
}
