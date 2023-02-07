package com.example.hanebaapi.loginApp.repository;

import com.example.hanebaapi.loginApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String name);
}
