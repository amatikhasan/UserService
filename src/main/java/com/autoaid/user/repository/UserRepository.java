package com.autoaid.user.repository;

import com.autoaid.user.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
    Optional<UserInfo> findByEmailAndPassword(String email, String password);
}
