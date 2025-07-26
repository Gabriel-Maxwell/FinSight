package com.SpendControl.maxwell.SpendControl.mapper;

import java.util.Collections;
import java.util.List;

import com.SpendControl.maxwell.SpendControl.domain.Transaction;
import com.SpendControl.maxwell.SpendControl.dto.TransactionDto; // Só se existir!
import com.SpendControl.maxwell.SpendControl.entity.TransactionEntity;

public abstract class TransactionMapper {
    public static Transaction toDomain(TransactionEntity entity) {
        if (entity == null) return null;
        return Transaction.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .date(entity.getDate())
                .description(entity.getDescription())
                .category(CategoryMapper.toDomain(entity.getCategory()))
                .build();
    }

    public static List<Transaction> toDomain(List<TransactionEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();
        return entities.stream().map(TransactionMapper::toDomain).toList();
    } 

    public static TransactionEntity fromDomain(Transaction transaction) {
        if (transaction == null) return null;
        return TransactionEntity.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .description(transaction.getDescription())
                .category(CategoryMapper.fromDomain(transaction.getCategory()))
                .build();
    }

 
    public static Transaction toDomain(TransactionDto dto) {
        if (dto == null) return null;
        return Transaction.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .description(dto.getDescription())
                .build();
    }

    public static TransactionDto fromDomainToDto(Transaction transaction) {
        if (transaction == null) return null;
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .build();
    }

    public static List<TransactionDto> fromDomainToDto(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) return Collections.emptyList();
        return transactions.stream().map(TransactionMapper::fromDomainToDto).toList();
    }
}
