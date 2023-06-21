package com.project.schoolService.repository;

import com.project.schoolService.model.LaptopPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopPartRepository extends JpaRepository<LaptopPart,Long> {
}
