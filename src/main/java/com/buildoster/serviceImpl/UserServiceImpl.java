package com.buildoster.serviceImpl;

import com.buildoster.model.User;
import com.buildoster.repository.UserRepository;
import com.buildoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public int updateUser(User user) {
        System.out.println(user);
        return userRepository.updateUserProfile(user.getF_name(), user.getL_name(), user.getEmail(), user.getPhone_number(), user.getId());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserProfile(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updatePassword(String password, Long userId) {

    }
}
