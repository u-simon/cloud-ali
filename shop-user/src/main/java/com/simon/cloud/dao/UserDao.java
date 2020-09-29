package com.simon.cloud.dao;

import com.simon.cloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
