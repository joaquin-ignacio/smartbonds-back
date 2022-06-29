package com.upc.smartbonds.service;

import com.upc.smartbonds.entity.User;
import com.upc.smartbonds.resource.AuthenticationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long userId);

    User createUser(User user);

    User updateUser(Long userId, User userDetails);

    ResponseEntity<?> deleteUser(Long userId);

    User authenticate (AuthenticationRequest request);
}
