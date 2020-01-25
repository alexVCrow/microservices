package ua.alexcrow.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.alexcrow.userservice.dto.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/status/check")
    public String status(){
        return "User Service Success";
    }

    @PostMapping
    public ResponseEntity checkPost(@RequestBody User user){
        return ResponseEntity.ok(user);
    }
}
