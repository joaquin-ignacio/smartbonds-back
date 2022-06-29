package com.upc.smartbonds.service.impls;

import com.upc.smartbonds.entity.User;
import com.upc.smartbonds.exception.ResourceNotFoundException;
import com.upc.smartbonds.repository.UserRepository;
import com.upc.smartbonds.resource.AuthenticationRequest;
import com.upc.smartbonds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setName(userDetails.getName());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User authenticate(AuthenticationRequest request) {
        User userAuth = userRepository.findByUsername(request.getUsername());
        return userRepository.findById(userAuth.getId()).orElseThrow(()->new ResourceNotFoundException("User", "Id", userAuth.getId()));
    }
}
