package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.domain.entity.BorrowingTransaction;
import com.example.tharaa.domain.entity.Member;
import com.example.tharaa.domain.enums.TransactionStatus;
import com.example.tharaa.dto.request.BorrowingTransactionRequestDto;
import com.example.tharaa.dto.response.BorrowingTransactionResponseDto;
import com.example.tharaa.exception.ResourceNotFoundException;
import com.example.tharaa.mapper.BorrowingTransactionMapper;
import com.example.tharaa.repository.BookRepository;
import com.example.tharaa.repository.BorrowingTransactionRepository;
import com.example.tharaa.repository.MemberRepository;
import com.example.tharaa.service.BorrowingTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingTransactionServiceImpl implements BorrowingTransactionService {

    private final BorrowingTransactionRepository transactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowingTransactionMapper transactionMapper;

    @Override
    public List<BorrowingTransactionResponseDto> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toResponse)
                .toList();
    }

    @Override
    public BorrowingTransactionResponseDto getTransactionById(Long id) {
        BorrowingTransaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return transactionMapper.toResponse(transaction);
    }

    @Override
    public BorrowingTransactionResponseDto borrowBook(BorrowingTransactionRequestDto dto) {
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + dto.memberId()));
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + dto.bookId()));

        BorrowingTransaction transaction = transactionMapper.toEntity(dto);
        transaction.setMember(member);
        transaction.setBook(book);

        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public BorrowingTransactionResponseDto returnBook(Long transactionId) {
        BorrowingTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + transactionId));

        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus(TransactionStatus.RETURNED);

        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public List<BorrowingTransactionResponseDto> getOverdueTransactions() {
        LocalDate today = LocalDate.now();
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> tx.getDueDate() != null &&
                        tx.getReturnDate() == null &&
                        tx.getDueDate().isBefore(today))
                .peek(tx -> tx.setStatus(TransactionStatus.OVERDUE))
                .map(transactionMapper::toResponse)
                .toList();
    }
}