package com.upc.smartbonds.controller;

import com.upc.smartbonds.entity.User;
import com.upc.smartbonds.resource.AuthenticationRequest;
import com.upc.smartbonds.resource.SaveUserResource;
import com.upc.smartbonds.resource.UserResource;
import com.upc.smartbonds.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        List<UserResource> resource = users.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resource, pageable, resource.size());
    }

    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.createUser(convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/auth")
    public UserResource authenticate(@RequestBody AuthenticationRequest request) {
        return convertToResource(userService.authenticate(request));
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
