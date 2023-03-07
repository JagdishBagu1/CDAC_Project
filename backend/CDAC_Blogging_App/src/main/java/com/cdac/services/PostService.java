package com.cdac.services;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.PostDTO;
import com.cdac.entities.Category;
import com.cdac.entities.Post;
import com.cdac.entities.User;
import com.cdac.repositories.CategoryRepo;
import com.cdac.repositories.PostRepo;
import com.cdac.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private static final String DEFAULT_IMAGE_URL = "default.png";

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    public Post insertPost(long userId, long categoryId, PostDTO postDTO) {
        User user = userService.getUserById(userId);
        Category category = categoryService.getCategoryById(categoryId);

        Post post = dtoToPost(postDTO);
        post.setImageUrl(DEFAULT_IMAGE_URL);
        post.setCategory(category);
        post.setUser(user);

        post = postRepo.save(post);

        return post;
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post getPostById(long id) {
        Optional<Post> postRes = postRepo.findById(id);
        if (postRes.isEmpty()) throw new ResourceNotFoundException("Post not found with the id: " + id);

        return postRes.get();
    }

    public Post updatePost(long id, PostDTO updatedPostDTO) {
        Post post = getPostById(id);

        post = dtoToPost(updatedPostDTO);
        post.setId(id);

        post = postRepo.save(post);

        return post;
    }

    // Delete the existing Post from the DB -
    public Post deletePost(long id) {
        Post post = getPostById(id);
        postRepo.deleteById(id);

        return post;
    }

    private Post dtoToPost(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }

    private PostDTO postToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

}
