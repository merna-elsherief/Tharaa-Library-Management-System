package com.example.tharaa.dto.request;

import java.time.LocalDate;

public record BorrowingTransactionRequestDto(
        Long memberId,
        Long bookId,
        LocalDate borrowDate,
        LocalDate dueDate
) {}
