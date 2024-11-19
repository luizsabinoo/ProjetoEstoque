package com.example.project_stock.repositories;

import com.example.project_stock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository  extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
