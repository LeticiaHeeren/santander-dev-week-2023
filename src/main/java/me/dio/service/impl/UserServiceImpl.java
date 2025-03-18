package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void followUser(Long userId, Long followerId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        User follower = userRepository.findById(followerId).orElseThrow(NoSuchElementException::new);
        user.getFollowers().add(follower);
        userRepository.save(user);
    }
}