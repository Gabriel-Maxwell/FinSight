package com.SpendControl.maxwell.SpendControl.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
        private Long id;
        private String name;
        private String type;
        private String description;
        private BigDecimal goal;
        private BigDecimal spent;
}
