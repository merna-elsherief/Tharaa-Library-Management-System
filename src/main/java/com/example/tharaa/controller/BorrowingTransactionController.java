package com.example.tharaa.controller;

import com.example.tharaa.dto.request.BorrowingTransactionRequestDto;
import com.example.tharaa.dto.response.BorrowingTransactionResponseDto;
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
    public List<BorrowingTransactionResponseDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingTransactionResponseDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransactionResponseDto> borrowBook(@RequestBody BorrowingTransactionRequestDto dto) {
        return ResponseEntity.ok(transactionService.borrowBook(dto));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<BorrowingTransactionResponseDto> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.returnBook(id));
    }

    @GetMapping("/overdue")
    public List<BorrowingTransactionResponseDto> getOverdueTransactions() {
        return transactionService.getOverdueTransactions();
    }
}
