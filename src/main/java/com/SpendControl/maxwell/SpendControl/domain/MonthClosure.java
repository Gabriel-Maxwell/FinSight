package com.SpendControl.maxwell.SpendControl.domain;

import lombok.Data;


@Data
public class MonthClosure {
    private Long id;
    private boolean closed;
    private User user;
    private MonthlyPlan monthlyPlan;

}