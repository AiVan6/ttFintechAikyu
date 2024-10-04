package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.entitys.VerifiedName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifiedNameRepository extends JpaRepository<VerifiedName, Long> {
}
