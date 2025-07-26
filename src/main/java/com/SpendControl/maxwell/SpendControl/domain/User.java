package com.SpendControl.maxwell.SpendControl.domain;

import com.SpendControl.maxwell.SpendControl.enums.UserProfile;
import com.SpendControl.maxwell.SpendControl.observer.UserObserver;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Builder
@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private BigDecimal salary;
    private UserProfile profile;
    private List<UserObserver> observers;

//    public User(List<UserObserver> observers) {
//        this.observers = new ArrayList<>(observers);
//    }

    public void setSalary(BigDecimal salary) {
        BigDecimal oldSalary = this.salary;
        this.salary = salary;
        notifyObservers(this.salary.subtract(oldSalary));
    }

    public void addObserver(UserObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(BigDecimal addValue) {
        for(UserObserver observer: observers) {
            observer.updateSalary(id,addValue);
        }
    }
}
