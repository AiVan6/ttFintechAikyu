package com.example.ttwebzaim.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account_info")
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_bureau_id", nullable = false)
    private CreditBureau creditBureau;

    @Column(nullable = false, name = "account_number")
    @JsonProperty("account_number")
    private String accountNumber;

    @Column(name = "account_status")
    @JsonProperty("account_status")
    private String accountStatus;

    @Column(name = "current_balance")
    @JsonProperty("current_balance")
    private BigDecimal currentBalance;

    @Column(name = "date_opened")
    @JsonProperty("date_opened")
    private LocalDate dateOpened;

    @Column(name = "days_in_arrears")
    @JsonProperty("days_in_arrears")
    private Integer daysInArrears;

    @Column(name = "delinquency_code")
    @JsonProperty("delinquency_code")
    private String delinquencyCode;

    @Column(name = "highest_days_in_arrears")
    @JsonProperty("highest_days_in_arrears")
    private Integer highestDaysInArrears;

    @Column(name = "is_your_account")
    @JsonProperty("is_your_account")
    private Boolean isYourAccount;

    @Column(name = "last_payment_amount")
    @JsonProperty("last_payment_amount")
    private BigDecimal lastPaymentAmount;

    @Column(name = "last_payment_date")
    @JsonProperty("last_payment_date")
    private LocalDate lastPaymentDate;

    @Column(name = "loaded_at")
    @JsonProperty("loaded_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate loadedAt;

    @Column(name = "original_amount")
    @JsonProperty("original_amount")
    private BigDecimal originalAmount;

    @Column(name = "overdue_balance")
    @JsonProperty("overdue_balance")
    private BigDecimal overdueBalance;

    @Column(name = "overdue_date")
    @JsonProperty("overdue_date")
    private LocalDate overdueDate;

    @Column(name = "product_type_id")
    @JsonProperty("product_type_id")
    private Integer productTypeId;
}
