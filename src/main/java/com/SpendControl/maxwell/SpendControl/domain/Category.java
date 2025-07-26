package com.SpendControl.maxwell.SpendControl.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Category {

    private Long id;
    private String name;
    private String type;
    private String description;
    private BigDecimal goal;
    private BigDecimal spent;
    private List<Transaction> transactions;
}
