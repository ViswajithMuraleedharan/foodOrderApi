package com.example.foodOrderApi.dao;

import com.example.foodOrderApi.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<user,Integer> {
    @Query(value = "SELECT * from tbl_user where userName=:username and password=:Password",nativeQuery = true)
    public List<user> getUser(String username, String Password);

}
