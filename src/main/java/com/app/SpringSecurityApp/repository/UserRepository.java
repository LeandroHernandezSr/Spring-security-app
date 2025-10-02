package com.app.SpringSecurityApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.SpringSecurityApp.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    Optional<UserEntity>findUserEntityByUserName(String username);
}
