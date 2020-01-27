package ua.alexcrow.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.alexcrow.userservice.dto.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/status/check")
    public ResponseEntity status(){
        return ResponseEntity.status(500).body(new User("test", "test"));
    }

    @PostMapping
    public ResponseEntity checkPost(@RequestBody User user){
        return ResponseEntity.ok(user);
    }
}
