package com.cdac.services;

import com.cdac.constants.Constants;
import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.PostDTO;
import com.cdac.entities.Category;
import com.cdac.entities.Post;
import com.cdac.entities.User;
import com.cdac.repositories.PostRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${project.images}")
    private String path;

    @Autowired
    private ObjectMapper mapper;

    public PostDTO insertPost(long userId, long categoryId, MultipartFile image, String postData) throws IOException {
        User user = userService.dtoToUser(userService.getUserById(userId));
        Category category = categoryService.dtoToCategory(categoryService.getCategoryById(categoryId));

        // code for image copy
        String imageName = image.getOriginalFilename();
        // for avoiding naming conflicts we will use UUID
        String randomImageName = UUID.randomUUID().toString();
        randomImageName = randomImageName.concat(imageName.substring(imageName.lastIndexOf(".")));

        String fullPath = path + File.separator + randomImageName;

        // creating folder images if not exists
        File file = new File(path);
        if (!file.exists()) file.mkdir();

        // then copy the image coming from frontend
        Files.copy(image.getInputStream(), Path.of(fullPath));

        PostDTO postDTO = mapper.readValue(postData, PostDTO.class);
        Post post = dtoToPost(postDTO);
        post.setImageUrl(randomImageName);
        post.setUser(user);
        post.setCategory(category);

        post = postRepo.save(post);

        return postToDTO(post);
    }

    public InputStream serveImage(String imageName) throws IOException {
        String fullPath = path + File.separator + imageName;
        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }

    public List<PostDTO> getAllPosts() {
        return postRepo.findAll().stream().map((post) -> postToDTO(post)).collect(Collectors.toList());
    }

    public PostDTO getPostById(long id) {
        Optional<Post> postRes = postRepo.findById(id);
        if (postRes.isEmpty()) throw new ResourceNotFoundException("Post not found with the id: " + id);

        return postToDTO(postRes.get());
    }

    public List<PostDTO> getAllPostsByUser(long id) {
        User user = userService.dtoToUser(userService.getUserById(id));
        List<Post> posts = postRepo.findAllByUser(user);

        return posts.stream().map((post -> postToDTO(post))).collect(Collectors.toList());
    }

    public List<PostDTO> getAllPostsByCategory(long id) {
        Category category = categoryService.dtoToCategory(categoryService.getCategoryById(id));
        List<Post> posts = postRepo.findAllByCategory(category);

        return posts.stream().map((post -> postToDTO(post))).collect(Collectors.toList());
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

    public List<PostDTO> search(String keywords) {
        return postRepo.findByTitleContaining(keywords).stream().map(post -> postToDTO(post)).collect(Collectors.toList());
    }

    public Post dtoToPost(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }

    public PostDTO postToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

}
