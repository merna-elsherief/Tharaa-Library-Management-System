package com.example.tharaa.controller;

import com.example.tharaa.domain.entity.BorrowingTransaction;
import com.example.tharaa.service.BorrowingTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class BorrowingTransactionController {

    private final BorrowingTransactionService transactionService;

    @GetMapping
    public List<BorrowingTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingTransaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransaction> borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return ResponseEntity.ok(transactionService.borrowBook(memberId, bookId));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingTransaction> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.returnBook(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
