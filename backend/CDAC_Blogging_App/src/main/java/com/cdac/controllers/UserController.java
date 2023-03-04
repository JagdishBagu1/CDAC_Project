package com.cdac.controllers;

import com.cdac.dtos.UserDTO;
import com.cdac.entities.User;
import com.cdac.services.UserService;
import com.cdac.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<ApiResponse<User>> handleInsertUser(@Valid @RequestBody UserDTO userDTO) {
//		System.out.println(userDTO);
        return ResponseEntity.ok(
                ApiResponse
                        .<User>builder()
                        .body(userService.insertUser(userDTO))
                        .success(true)
                        .message("User has been created successfully!")
                        .build()
        );
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<User>>> handleAllUsers() {
        return ResponseEntity.ok(
                ApiResponse
                        .<List<User>>builder()
                        .success(true)
                        .body(userService.getAllUsers())
                        .message("Fetched all users!")
                        .build()
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<User>> handleSingleUser(@PathVariable long id) {
        return ResponseEntity.ok(
                ApiResponse
                        .<User>builder()
                        .success(true)
                        .body(userService.getUserById(id))
                        .message("Fetched single user with id: " + id)
                        .build()
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> handleUpdate(@PathVariable long id, @Valid @RequestBody UserDTO updatedUserDTO) {
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .success(true)
                        .body(userService.updateUser(id, updatedUserDTO))
                        .message("User has been updated successfully!")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<User>> handleDelete(@PathVariable long id) {
        return ResponseEntity.ok(
                ApiResponse
                        .<User>builder()
                        .success(true)
                        .body(userService.deleteUser(id))
                        .message("User has been deleted successfully!")
                        .build()
        );
    }

}
