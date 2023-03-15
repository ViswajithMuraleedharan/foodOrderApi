package com.example.foodOrderApi.controller;

import com.example.foodOrderApi.dao.UserRepository;
import com.example.foodOrderApi.dao.foodRepository;
import com.example.foodOrderApi.dao.orderRepository;
import com.example.foodOrderApi.model.food;
import com.example.foodOrderApi.model.order;
import com.example.foodOrderApi.model.user;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class orderController {
    @Autowired
    UserRepository userrepository;
    @Autowired
    foodRepository foodrepository;
    @Autowired
    orderRepository orderrepository;
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody String orderrequest){
        JSONObject errorList=validateOrder(orderrequest);
        if(!errorList.isEmpty()){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
        else{
            order order1=setOrder(orderrequest);
            order order2=orderrepository.save(order1);
            return new ResponseEntity<>("order created at orderid "+order2.getOrderId(),HttpStatus.OK);
        }
    }

    public order setOrder(String orderrequest){
        JSONObject jsonObject=new JSONObject(orderrequest);
        order order1=new order();
        String userId= jsonObject.getString("userId");
        user user1=userrepository.findById(Integer.valueOf(userId)).get();
        order1.setUserId(user1);
        String foodId= jsonObject.getString("foodId");
        food food1=foodrepository.findById(Integer.valueOf(foodId)).get();
        order1.setFoodId(food1);
        return order1;
    }

    private JSONObject validateOrder(String orderrequest) {
        JSONObject object=new JSONObject(orderrequest);
        JSONObject errorList=new JSONObject();
        if(!object.has("userId")){
            errorList.put("userId","missing parameter");
        }
        else {
            String userId = object.getString("userId");
            user user1=userrepository.findById(Integer.valueOf(userId)).get();
            if(user1==null){
                errorList.put("userId","User by userId does not exists");
            }
        }
        if(!object.has("foodId")){
            errorList.put("foodId","missing parameter");
        }
        else {
            String foodId = object.getString("foodId");
            food food1=foodrepository.findById(Integer.valueOf(foodId)).get();
            if(food1==null){
                errorList.put("foodId","Food item does not exists");
            }
        }
        return errorList;
    }

    @GetMapping("/getOrder")
    public ResponseEntity<String> getOrder(@Nullable @RequestParam String orderId){
        List<order> orders=new ArrayList<>();
        if(orderId==null){
            orders=orderrepository.findAll();
        }
        else{
            order order1=orderrepository.findById(Integer.valueOf(orderId)).get();
            orders.add(order1);
        }
        return new ResponseEntity<>(orders.toString(),HttpStatus.OK);
    }
}
