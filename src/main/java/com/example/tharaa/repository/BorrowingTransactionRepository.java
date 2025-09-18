package com.example.tharaa.repository;

import com.example.tharaa.domain.entity.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByMemberId(Long memberId);
    List<BorrowingTransaction> findByBookId(Long bookId);
}
