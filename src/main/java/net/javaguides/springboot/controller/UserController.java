package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Step 2//Again Step 5
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    //Build create User Rest APIs
    @PostMapping
    public ResponseEntity<UserDto>createUser(@RequestBody UserDto user){//step2 <User> to <UserDto>// Go to userService
      //User to UserDto step 5
         UserDto savedUser= userService.createUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    //build get user by id REST API
    //http://localhost:8080/api/users/1
    @GetMapping("{id}")//URI TEMPLATE VARIABLE
    public  ResponseEntity<UserDto>getUserById(@PathVariable("id") Long userId){
        UserDto user=userService.getUserById(userId);
        return new ResponseEntity<>( user,HttpStatus.OK);

    }

   //Build Get All User REST API
   //http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    //Build Update User REST API
    //http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserDto user){
        user.setId(userId);
        UserDto updateUser=userService.updateUser(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    //delete User rest Api
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully Deleted!",HttpStatus.OK);
    }

}
