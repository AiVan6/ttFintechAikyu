package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.entitys.CreditBureau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditBureauRepository extends JpaRepository<CreditBureau, Long> {
}
