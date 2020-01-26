package ua.alexcrow.carservice.controller;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private UsersServiceProxy usersServiceProxy;

    @GetMapping("/status")
    public ResponseEntity status(){
        return ResponseEntity.ok(usersServiceProxy.usersStatusCheck().getBody());
    }
}
