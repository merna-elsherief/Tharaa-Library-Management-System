package com.example.tharaa.service.impl;

import com.example.tharaa.domain.entity.Book;
import com.example.tharaa.domain.entity.BorrowingTransaction;
import com.example.tharaa.domain.entity.Member;
import com.example.tharaa.exception.ResourceNotFoundException;
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

    @Override
    public List<BorrowingTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public BorrowingTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    public BorrowingTransaction borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        BorrowingTransaction transaction = BorrowingTransaction.builder()
                .member(member)
                .book(book)
                .borrowedDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .returned(false)
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    public BorrowingTransaction returnBook(Long transactionId) {
        BorrowingTransaction transaction = getTransactionById(transactionId);
        transaction.setReturned(true);
        transaction.setReturnDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
