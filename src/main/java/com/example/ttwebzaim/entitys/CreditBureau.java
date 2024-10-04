package com.example.ttwebzaim.entitys;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "credit_bureau")
public class CreditBureau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "creditBureau", cascade = CascadeType.ALL)
    @JsonProperty("account_info")
    private List<AccountInfo> accountInfos;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//lazy?
    @JoinColumn(name = "verified_name_id", nullable = false, referencedColumnName = "id")
    @JsonProperty("verified_name")
    private VerifiedName verifiedName;

    @OneToOne(mappedBy = "creditBureau")
    private LoanRequest loanRequest;

}
