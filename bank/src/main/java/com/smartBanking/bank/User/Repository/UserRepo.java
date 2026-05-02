package com.smartBanking.bank.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartBanking.bank.User.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
    boolean existsByEmail(String email);
    
    User findByName(String name);
}
