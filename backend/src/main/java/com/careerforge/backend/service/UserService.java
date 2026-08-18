package com.careerforge.backend.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.careerforge.backend.entity.User;
import com.careerforge.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(User user) {
    return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
}
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {
    User existingUser = userRepository.findById(id).orElse(null);

    if (existingUser == null) {
        return null;
    }
    existingUser.setName(updatedUser.getName());
    existingUser.setEmail(updatedUser.getEmail());
    return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}