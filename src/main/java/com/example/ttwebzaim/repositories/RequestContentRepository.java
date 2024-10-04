package com.example.ttwebzaim.repositories;

import com.example.ttwebzaim.entitys.RequestContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestContentRepository extends JpaRepository<RequestContent, Long> {
}
