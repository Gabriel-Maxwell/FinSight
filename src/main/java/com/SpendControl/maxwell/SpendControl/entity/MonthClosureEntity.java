package com.SpendControl.maxwell.SpendControl.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="month_clojure")
public class MonthClosureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean closed;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private MonthlyPlanEntity monthlyPlan;

}