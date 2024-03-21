package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponce.ApiResponce;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> users=userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponce("user added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isupdated=userService.updateUser(id,user);
        if(isupdated){
            return ResponseEntity.status(200).body(new ApiResponce("user updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean isdeleted=userService.deleteUser(id);
        if(isdeleted){
            return ResponseEntity.status(200).body(new ApiResponce("user deleted"));
        }
        return ResponseEntity.status(400).body("not found");
    }


@GetMapping("/buy/{userid}/{productid}/{merchantid}")
    public ResponseEntity userbuy(@PathVariable int userid,@PathVariable int productid,@PathVariable int merchantid){
        boolean isbuying=userService.userbuy(userid,productid,merchantid);
       if(isbuying){
            return ResponseEntity.status(200).body(new ApiResponce("buying completed"));
        }
        return ResponseEntity.status(400).body((new ApiResponce("not complete")));
    }


    @GetMapping("/wishlist/{userid}/{productid}")
    public ResponseEntity wishlist(@PathVariable int userid,@PathVariable int productid){
        ArrayList<Product> pp=userService.wishlist(userid,productid);
        if(pp==null){
            return ResponseEntity.status(400).body(new ApiResponce("not found"));
        }
        return ResponseEntity.status(200).body(pp);
    }



}
