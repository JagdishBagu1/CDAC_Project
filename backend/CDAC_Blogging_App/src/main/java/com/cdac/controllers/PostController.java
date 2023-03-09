package com.cdac.controllers;

import com.cdac.dtos.PostDTO;
import com.cdac.services.PostService;
import com.cdac.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/categories/{categoryId}")
    ResponseEntity<ApiResponse<PostDTO>> controlCreatePost(@PathVariable long userId, @PathVariable long categoryId, @Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PostDTO>builder().body(postService.insertPost(userId, categoryId, postDTO)).success(true).message("Post created successfully!").build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<PostDTO>>> controlAllPosts() {
        return ResponseEntity.ok(ApiResponse.<List<PostDTO>>builder().body(postService.getAllPosts()).success(true).message("Fetched all posts successfully!").build());
    }

    @GetMapping("/{postId}")
    ResponseEntity<ApiResponse<PostDTO>> controlSinglePost(@PathVariable long postId) {
        return ResponseEntity.ok(ApiResponse.<PostDTO>builder().body(postService.getPostById(postId)).success(true).message("Fetched single Post with id: " + postId).build());
    }

    @PutMapping("/{postId}")
    ResponseEntity<ApiResponse<PostDTO>> controlUpdatePost(@PathVariable long postId, @Valid @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(ApiResponse.<PostDTO>builder().body(postService.updatePost(postId, postDTO)).success(true).message("Updated Post with id: " + postId).build());
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<ApiResponse<PostDTO>> controlDeletePost(@PathVariable long postId) {
        return ResponseEntity.ok(ApiResponse.<PostDTO>builder().body(postService.deletePost(postId)).success(true).message("Deleted Post with id: " + postId).build());
    }

}
