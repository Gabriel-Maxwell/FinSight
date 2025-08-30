package com.SpendControl.maxwell.SpendControl.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.SpendControl.maxwell.SpendControl.dto.TransactionDto;
import com.SpendControl.maxwell.SpendControl.repository.TransactionRepository;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;

    

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }



    public TransactionDto createTransaction(TransactionDto transactionDto) {
        return transactionDto;
    }    

    public void validateTransaction(TransactionDto transactionDto) {
        if (transactionDto == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        if (transactionDto.getDescription() == null || transactionDto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Transaction description cannot be null or empty");
        }
        if (transactionDto.getAmount() == null || transactionDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }
        if (transactionDto.getDate() == null) {
            throw new IllegalArgumentException("Transaction date cannot be null");
        }
        if (transactionDto.getCategoryName() == null || transactionDto.getCategoryId() == null) {
            throw new IllegalArgumentException("Transaction category cannot be null or have a null ID");
        }
    
    }

    
}
