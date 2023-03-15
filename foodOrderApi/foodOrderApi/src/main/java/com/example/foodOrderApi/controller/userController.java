package com.example.foodOrderApi.controller;

import com.example.foodOrderApi.dao.UserRepository;
import com.example.foodOrderApi.model.user;
import com.example.foodOrderApi.service.userService;
import com.example.foodOrderApi.utils.CommonUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {
    @Autowired
    userService service;
    @Autowired
    UserRepository repository;

    public boolean validateAdmin(String userName, String password) {
        List<user> userList=repository.getUser(userName,password);
        if(userList.isEmpty()){
            return false;
        }
        else{
            user user1=userList.get(0);
            boolean admin=user1.isAdmin();
            return admin;
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody String userRequest){
        JSONObject errorList=isValidated(userRequest);
        user user1=null;
        if(!errorList.isEmpty()){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
        else{
            user1=service.saveUser(userRequest);
            int userId=user1.getUserId();
            return new ResponseEntity<>("user created successfully at id "+userId,HttpStatus.OK);
        }

    }
    @GetMapping("/getUser")
    public ResponseEntity<String> getuser(){
        List<user> users=repository.findAll();
        return new ResponseEntity<>(users.toString(),HttpStatus.OK);
    }

    private JSONObject isValidated(String userRequest) {
        JSONObject object=new JSONObject(userRequest);
        JSONObject errorList=new JSONObject();
        if(object.has("userName")){
           String username=object.getString("userName");
           if (!CommonUtils.isValidUsername(username)){
               errorList.put("userName","invalid userName");
           }
        }
        else{
            errorList.put("userName","missing parameter");
        }
        if(object.has("password")){
            String password=object.getString("password");
            if (!CommonUtils.isValidPassword(password)){
                errorList.put("password","Please enter valid password eg: Akshay@12390");
            }
        }
        if(!object.has("firstName")){
            errorList.put("firstName","missing parameter");
        }
        if(!object.has("location")){
            errorList.put("location","missing parameter");
        }
        return errorList;
    }

}
