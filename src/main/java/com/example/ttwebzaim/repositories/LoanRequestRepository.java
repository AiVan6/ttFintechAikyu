package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.entitys.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
}
