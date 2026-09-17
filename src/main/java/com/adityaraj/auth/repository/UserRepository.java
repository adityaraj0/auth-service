package com.adityaraj.auth.repository;

import com.adityaraj.auth.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByEmailOrUserName(String email, String userName);
}
