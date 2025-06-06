package com.aphexa.discovery.service;

import com.aphexa.discovery.model.User;
import com.aphexa.discovery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public User update(Long id, User userData) {
        return repository.findById(id).map(user -> {
            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            user.setLastFmUsername(userData.getLastFmUsername());
            return repository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
