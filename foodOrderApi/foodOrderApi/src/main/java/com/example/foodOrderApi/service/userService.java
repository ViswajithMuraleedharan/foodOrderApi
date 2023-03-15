package com.example.foodOrderApi.service;

import com.example.foodOrderApi.dao.UserRepository;
import com.example.foodOrderApi.model.user;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    UserRepository userRepository;
    public user setUser(String userRequest) {
        JSONObject object=new JSONObject(userRequest);
        user user1=new user();
        user1.setUserName(object.getString("userName"));
        user1.setPassword(object.getString("password"));
        user1.setFirstName(object.getString("firstName"));
        user1.setLastName(object.getString("lastName"));
        user1.setLocation(object.getString("location"));
        if(!object.has("admin")){
            user1.setAdmin(false);
        }
        else{
            boolean admin= object.getBoolean("admin");
           user1.setAdmin(admin);
        }
        return user1;
    }
    public user saveUser(String userRequest){
        user user1=setUser(userRequest);
        user createduser=userRepository.save(user1);
        return createduser;
    }

}
