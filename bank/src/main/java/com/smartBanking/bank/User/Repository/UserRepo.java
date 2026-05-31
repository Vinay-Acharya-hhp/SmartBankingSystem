package com.smartBanking.bank.User.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartBanking.bank.User.Entity.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
	Users findByEmail(String email);
    boolean existsByEmail(String email);
    
    Users findByName(String name);
}
