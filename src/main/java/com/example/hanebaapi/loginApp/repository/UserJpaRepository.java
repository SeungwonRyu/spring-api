package com.example.hanebaapi.loginApp.repository;

import com.example.hanebaapi.loginApp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    User findByEmail(String name);
}
