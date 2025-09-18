package com.example.tharaa.service;


import com.example.tharaa.domain.entity.BorrowingTransaction;

import java.util.List;

public interface BorrowingTransactionService {
    List<BorrowingTransaction> getAllTransactions();
    BorrowingTransaction getTransactionById(Long id);
    BorrowingTransaction borrowBook(Long memberId, Long bookId);
    BorrowingTransaction returnBook(Long transactionId);
    void deleteTransaction(Long id);
}
