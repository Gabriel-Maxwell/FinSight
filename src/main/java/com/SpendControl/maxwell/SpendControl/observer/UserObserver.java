package com.SpendControl.maxwell.SpendControl.observer;

import java.math.BigDecimal;

public interface UserObserver {
    void updateSalary(Long userId, BigDecimal addValue);
}
