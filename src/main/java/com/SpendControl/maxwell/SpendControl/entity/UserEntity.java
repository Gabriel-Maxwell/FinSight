package com.SpendControl.maxwell.SpendControl.entity;

import java.math.BigDecimal;
import java.util.List;

import com.SpendControl.maxwell.SpendControl.enums.UserProfile;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private UserProfile profile;
    @OneToMany(mappedBy = "owner")
    private List<MonthlyPlanEntity> ownedPlans;
    @OneToMany(mappedBy = "user")
    private List<FixedExpenseEntity> fixedExpenses;

}
