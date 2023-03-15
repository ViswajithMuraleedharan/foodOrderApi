package com.example.foodOrderApi.dao;

import com.example.foodOrderApi.model.food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface foodRepository extends JpaRepository<food,Integer> {
}
