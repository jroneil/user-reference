package com.oneil.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oneil.users.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}