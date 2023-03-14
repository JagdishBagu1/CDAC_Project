package com.cdac.controllers;

import com.cdac.dtos.RoleDTO;
import com.cdac.services.RoleService;
import com.cdac.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<ApiResponse<RoleDTO>> handleInsertUser(@Valid @RequestBody RoleDTO roleDTO) {
//		System.out.println(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<RoleDTO>builder().body(roleService.insertRole(roleDTO)).success(true).message("Role has been created successfully!").build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<RoleDTO>>> handleAllUsers() {
        return ResponseEntity.ok(ApiResponse.<List<RoleDTO>>builder().success(true).body(roleService.getAllRoles()).message("Fetched all roles!").build());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<RoleDTO>> handleSingleUser(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.<RoleDTO>builder().success(true).body(roleService.getRoleById(id)).message("Fetched single role with id: " + id).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<RoleDTO>> handleUpdate(@PathVariable long id, @Valid @RequestBody RoleDTO updatedRoleDTO) {
        return ResponseEntity.ok(ApiResponse.<RoleDTO>builder().success(true).body(roleService.updateRole(id, updatedRoleDTO)).message("Role has been updated successfully!").build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<RoleDTO>> handleDelete(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.<RoleDTO>builder().success(true).body(roleService.deleteROle(id)).message("Role has been deleted successfully!").build());
    }

}
