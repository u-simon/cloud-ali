package com.simon.cloud.dao;

import com.simon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<User, Integer> {
}
