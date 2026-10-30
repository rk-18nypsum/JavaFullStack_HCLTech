package com.hcld2.jwtdemo.repository;

import com.hcld2.jwtdemo.entity.User;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long>{
    Optional<User> findByUsername(String username);
}
