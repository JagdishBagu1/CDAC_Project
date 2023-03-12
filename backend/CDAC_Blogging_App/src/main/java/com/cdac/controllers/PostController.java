package com.cdac.controllers;

import com.cdac.dtos.PostDTO;
import com.cdac.services.PostService;
import com.cdac.utils.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/users/{userId}/categories/{categoryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ApiResponse<PostDTO>> controlCreatePost(@PathVariable long userId, @PathVariable long categoryId, @RequestParam("image") MultipartFile file, @RequestParam("postData") String postData) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PostDTO>builder().body(postService.insertPost(userId, categoryId, file, postData)).success(true).message("Post created successfully!").build());
    }

    @GetMapping(value = "/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    void handleServeImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = postService.serveImage(imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<PostDTO>>> controlAllPosts() {
        return ResponseEntity.ok(ApiResponse.<List<PostDTO>>builder().body(postService.getAllPosts()).success(true).message("Fetched all posts successfully!").build());
    }

    @GetMapping("/{postId}")
    ResponseEntity<ApiResponse<PostDTO>> controlSinglePost(@PathVariable long postId) {
        return ResponseEntity.ok(ApiResponse.<PostDTO>builder().body(postService.getPostById(postId)).success(true).message("Fetched single Post with id: " + postId).build());
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<ApiResponse<List<PostDTO>>> controlPostsByUser(@PathVariable long userId) {
        return ResponseEntity.ok(ApiResponse.<List<PostDTO>>builder().body(postService.getAllPostsByUser(userId)).success(true).message("Fetched all Posts with user id: " + userId).build());
    }

    @GetMapping("/categories/{categoryId}")
    ResponseEntity<ApiResponse<List<PostDTO>>> controlPostsByCategory(@PathVariable long categoryId) {
        return ResponseEntity.ok(ApiResponse.<List<PostDTO>>builder().body(postService.getAllPostsByCategory(categoryId)).success(true).message("Fetched all Posts with category id: " + categoryId).build());
    }

    @GetMapping("/search/{keywords}")
    ResponseEntity<ApiResponse<List<PostDTO>>> controlSearchPost(@PathVariable String keywords) {
        return ResponseEntity.ok(ApiResponse.<List<PostDTO>>builder().body(postService.search(keywords)).success(true).message("Fetched all searched Posts.").build());
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
