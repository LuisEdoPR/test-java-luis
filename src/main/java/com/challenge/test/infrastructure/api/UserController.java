package com.challenge.test.infrastructure.api;

import com.challenge.test.application.UserService;
import com.challenge.test.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("user/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = service.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("user/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping("user/newUser")
    public ResponseEntity<Void> newUser(@RequestBody User user) {
        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("user/updateUser")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        service.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("user/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
