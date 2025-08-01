package com.SpendControl.maxwell.SpendControl.observer;

import com.SpendControl.maxwell.SpendControl.entity.MonthlyPlanEntity;
import com.SpendControl.maxwell.SpendControl.repository.MonthlyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class MonthlyPlanManager implements UserObserver{

    @Autowired
    private MonthlyPlanRepository monthlyPlanRepository;

    @Override
    public void updateSalary(Long userId, BigDecimal addValue) {
        if (BigDecimal.ZERO.equals(addValue)) {
            return;
        }

        //locate the current and future monthly plans for the user
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        List<MonthlyPlanEntity> currentAndFuturePlans  = monthlyPlanRepository.findActivePlanByUserId(userId, month, year);
        // for every plan found, update the budget
        if (!currentAndFuturePlans.isEmpty()) {
            for(MonthlyPlanEntity currentPlan: currentAndFuturePlans) {
                // If user is owner, update plan budget to reflect new salary
                recalculateBudget(currentPlan, addValue);
                monthlyPlanRepository.save(currentPlan);
            }
        }

    }

    private void recalculateBudget(MonthlyPlanEntity plan, BigDecimal addValue) {
        BigDecimal newBudget = plan.getTotalBudget().add(addValue);
        plan.setTotalBudget(newBudget);
    }
}
