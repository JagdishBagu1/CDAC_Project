package com.cdac.services;

import com.cdac.constants.Constants;
import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.PostDTO;
import com.cdac.entities.Category;
import com.cdac.entities.Post;
import com.cdac.entities.User;
import com.cdac.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    public PostDTO insertPost(long userId, long categoryId, PostDTO postDTO) {
        User user = userService.dtoToUser(userService.getUserById(userId));
        Category category = categoryService.dtoToCategory(categoryService.getCategoryById(categoryId));

        Post post = dtoToPost(postDTO);
        post.setImageUrl(Constants.DEFAULT_POST_IMAGE_URL);
        post.setUser(user);
        post.setCategory(category);

        post = postRepo.save(post);

        return postToDTO(post);
    }

    public List<PostDTO> getAllPosts() {
        return postRepo.findAll().stream().map((post) -> postToDTO(post)).collect(Collectors.toList());
    }

    public PostDTO getPostById(long id) {
        Optional<Post> postRes = postRepo.findById(id);
        if (postRes.isEmpty()) throw new ResourceNotFoundException("Post not found with the id: " + id);

        return postToDTO(postRes.get());
    }

    public PostDTO updatePost(long id, PostDTO updatedPostDTO) {
        Post post = dtoToPost(getPostById(id));
        post.setTitle(updatedPostDTO.getTitle());
        post.setContent(updatedPostDTO.getContent());

        post = postRepo.save(post);

        return postToDTO(post);
    }

    // Delete the existing Post from the DB -
    public PostDTO deletePost(long id) {
        PostDTO postDTO = getPostById(id);
        postRepo.deleteById(id);

        return postDTO;
    }

    public Post dtoToPost(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }

    public PostDTO postToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

}
