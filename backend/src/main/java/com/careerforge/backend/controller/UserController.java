package com.careerforge.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.careerforge.backend.entity.User;
import com.careerforge.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
}
    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
   @GetMapping("/api/users/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {

    Optional<User> user = userService.getUserById(id);

    if (user.isPresent()) {
        return ResponseEntity.ok(user.get());
    }

    return ResponseEntity.notFound().build();
}
    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
         userService.deleteUser(id);

    return ResponseEntity.noContent().build();
    }
}