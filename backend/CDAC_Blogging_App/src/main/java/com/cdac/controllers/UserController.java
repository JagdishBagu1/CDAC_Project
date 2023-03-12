package com.cdac.controllers;

import com.cdac.dtos.RoleDTO;
import com.cdac.dtos.UserDTO;
import com.cdac.services.UserService;
import com.cdac.utils.ApiResponse;
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
    ResponseEntity<ApiResponse<UserDTO>> handleInsertUser(@Valid @RequestBody UserDTO userDTO) {
//		System.out.println(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<UserDTO>builder().body(userService.insertUser(userDTO)).success(true).message("User has been created successfully!").build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<UserDTO>>> handleAllUsers() {
        return ResponseEntity.ok(ApiResponse.<List<UserDTO>>builder().success(true).body(userService.getAllUsers()).message("Fetched all users!").build());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserDTO>> handleSingleUser(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.<UserDTO>builder().success(true).body(userService.getUserById(id)).message("Fetched single user with id: " + id).build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<UserDTO>> handleUpdate(@PathVariable long id, @Valid @RequestBody UserDTO updatedUserDTO) {
        return ResponseEntity.ok(ApiResponse.<UserDTO>builder().success(true).body(userService.updateUser(id, updatedUserDTO)).message("User has been updated successfully!").build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<UserDTO>> handleDelete(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.<UserDTO>builder().success(true).body(userService.deleteUser(id)).message("User has been deleted successfully!").build());
    }

}
