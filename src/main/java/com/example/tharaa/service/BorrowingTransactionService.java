package com.example.tharaa.service;


import com.example.tharaa.domain.entity.BorrowingTransaction;

import java.util.List;

import com.example.tharaa.dto.request.BorrowingTransactionRequestDto;
import com.example.tharaa.dto.response.BorrowingTransactionResponseDto;

import java.util.List;

public interface BorrowingTransactionService {
    List<BorrowingTransactionResponseDto> getAllTransactions();
    BorrowingTransactionResponseDto getTransactionById(Long id);
    BorrowingTransactionResponseDto borrowBook(BorrowingTransactionRequestDto dto);
    BorrowingTransactionResponseDto returnBook(Long transactionId);
    List<BorrowingTransactionResponseDto> getOverdueTransactions();
}