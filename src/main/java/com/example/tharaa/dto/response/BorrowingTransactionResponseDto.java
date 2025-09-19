package com.example.tharaa.dto.response;

import com.example.tharaa.domain.enums.TransactionStatus;

import java.time.LocalDate;

public record BorrowingTransactionResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Long bookId,
        String bookTitle,
        LocalDate borrowDate,
        LocalDate dueDate,
        LocalDate returnDate,
        TransactionStatus status
) {}
