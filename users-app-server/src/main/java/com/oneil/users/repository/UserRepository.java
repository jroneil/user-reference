package com.oneil.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneil.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}