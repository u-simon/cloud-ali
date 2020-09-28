package com.simon.cloud.dao;

import com.simon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<User, Integer> {
}
