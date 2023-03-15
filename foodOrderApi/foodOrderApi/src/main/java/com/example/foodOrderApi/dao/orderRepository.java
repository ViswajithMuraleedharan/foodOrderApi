package com.example.foodOrderApi.dao;

import com.example.foodOrderApi.model.order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<order,Integer> {
}
