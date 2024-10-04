package com.example.ttwebzaim.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "loan_request")
public class LoanRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,name = "loan_uui_request")
    @JsonProperty("loanRequestID")
    private String loanUUIRequest;


//    @OneToOne(mappedBy = "loanRequest")
//    private RequestContent requestContent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//lazy?
    @JoinColumn(name = "reg_presin_id", nullable = false, referencedColumnName = "id")
    private RegPerson regPerson;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//layzy?
    @JoinColumn(nullable = false,name = "credit_bureau_id", referencedColumnName = "id")
    private CreditBureau creditBureau;
}
