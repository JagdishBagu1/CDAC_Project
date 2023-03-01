package com.cdac.controllers;

import com.cdac.dtos.UserDTO;
import com.cdac.entities.User;
import com.cdac.payload.ApiResponse;
import com.cdac.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                        .success(true)
                        .body(userService.insertUser(userDTO))
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

    @GetMapping("/{email}")
    ResponseEntity<ApiResponse<User>> handleSingleUser(@PathVariable String email) {
        return ResponseEntity.ok(
                ApiResponse
                        .<User>builder()
                        .success(true)
                        .body(userService.getUserByEmail(email))
                        .message("Fetched single user: " + email)
                        .build()
        );
    }

    @PutMapping("/{email}")
    ResponseEntity<ApiResponse> handleUpdate(@PathVariable String email, @RequestBody UserDTO updatedUserDTO) {
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .success(true)
                        .body(userService.updateUser(email, updatedUserDTO))
                        .message("User has been updated successfully!")
                        .build()
        );
    }

    @DeleteMapping("/{email}")
    ResponseEntity<ApiResponse<User>> handleDelete(@PathVariable String email) {
        return ResponseEntity.ok(
                ApiResponse
                        .<User>builder()
                        .success(true)
                        .body(userService.deleteUser(email))
                        .message("User '" + email + "' has been deleted successfully!")
                        .build()
        );
    }

}
