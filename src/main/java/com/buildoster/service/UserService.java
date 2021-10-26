package com.buildoster.service;

import com.buildoster.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public Boolean existsByEmail(String email);
    public int updateUser(User user);
    public List<User> getAll();
    public Optional<User> getUserProfile(String email);
    void updatePassword(String password, Long userId);
}
