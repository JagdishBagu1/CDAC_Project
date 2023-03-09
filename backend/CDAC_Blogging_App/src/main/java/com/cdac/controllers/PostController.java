package com.cdac.controllers;

import com.cdac.dtos.PostDTO;
import com.cdac.services.PostService;
import com.cdac.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/categories/{categoryId}/posts")
    ResponseEntity<ApiResponse<PostDTO>> controlCreatePost(@PathVariable long userId, @PathVariable long categoryId, @Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(ApiResponse.<PostDTO>builder().body(postService.insertPost(userId, categoryId, postDTO)).success(true).message("Post created successfully!").build());
    }

    @GetMapping("/posts")
    ResponseEntity<ApiResponse<List<PostDTO>>> controlAllPosts() {
        return ResponseEntity.ok(ApiResponse.<List<PostDTO>>builder().body(postService.getAllPosts()).success(true).message("Fetched all posts successfully!").build());
    }

}
