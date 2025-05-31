package com.SpendControl.maxwell.SpendControl.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Category {
    //TODO Category Controller
    private Long id;
    private String name;
    private String type;
    private BigDecimal goal;
    private BigDecimal spent;
    private List<Transaction> transactions;
}
