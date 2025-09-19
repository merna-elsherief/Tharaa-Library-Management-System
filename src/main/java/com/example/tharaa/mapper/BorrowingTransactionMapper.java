package com.example.tharaa.mapper;

import com.example.tharaa.domain.entity.BorrowingTransaction;
import com.example.tharaa.dto.request.BorrowingTransactionRequestDto;
import com.example.tharaa.dto.response.BorrowingTransactionResponseDto;

public interface BorrowingTransactionMapper {
    BorrowingTransactionResponseDto toResponse(BorrowingTransaction transaction);
    BorrowingTransaction toEntity(BorrowingTransactionRequestDto dto);
    void updateEntityFromRequest(BorrowingTransactionRequestDto dto, BorrowingTransaction transaction);
}