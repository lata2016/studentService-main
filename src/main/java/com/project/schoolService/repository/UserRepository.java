package com.project.schoolService.repository;

import com.project.schoolService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
}
