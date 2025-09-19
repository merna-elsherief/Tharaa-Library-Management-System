package com.example.tharaa.mapper.impl;

import com.example.tharaa.domain.entity.BorrowingTransaction;
import com.example.tharaa.dto.request.BorrowingTransactionRequestDto;
import com.example.tharaa.dto.response.BorrowingTransactionResponseDto;
import com.example.tharaa.mapper.BorrowingTransactionMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowingTransactionMapperImpl implements BorrowingTransactionMapper {

    @Override
    public BorrowingTransactionResponseDto toResponse(BorrowingTransaction transaction) {
        return new BorrowingTransactionResponseDto(
                transaction.getId(),
                transaction.getMember().getId(),
                transaction.getMember().getFirstName() + " " + transaction.getMember().getLastName(),
                transaction.getBook().getId(),
                transaction.getBook().getTitle(),
                transaction.getBorrowDate(),
                transaction.getDueDate(),
                transaction.getReturnDate(),
                transaction.getStatus()
        );
    }

    @Override
    public BorrowingTransaction toEntity(BorrowingTransactionRequestDto dto) {
        return BorrowingTransaction.builder()
                .borrowDate(dto.borrowDate())
                .dueDate(dto.dueDate())
                .status(com.example.tharaa.domain.enums.TransactionStatus.BORROWED)
                .build();
    }

    @Override
    public void updateEntityFromRequest(BorrowingTransactionRequestDto dto, BorrowingTransaction transaction) {
        transaction.setBorrowDate(dto.borrowDate());
        transaction.setDueDate(dto.dueDate());
    }
}
