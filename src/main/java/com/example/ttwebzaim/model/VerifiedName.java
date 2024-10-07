package com.example.ttwebzaim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "verified_name")
public class VerifiedName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @JsonProperty("first_name")
    private String firstName;

    @Column(nullable = false, name = "other_name")
    @JsonProperty("other_name")
    private String otherName;

    @Column(nullable = false, name = "surname")
    private String surname;

    @OneToOne(mappedBy = "verifiedName")
    private CreditBureau creditBureau;

}
