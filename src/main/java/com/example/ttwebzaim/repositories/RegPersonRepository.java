package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.entitys.RegPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegPersonRepository extends JpaRepository<RegPerson, Long> {
}
