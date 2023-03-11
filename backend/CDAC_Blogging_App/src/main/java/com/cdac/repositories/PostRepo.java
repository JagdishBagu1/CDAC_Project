package com.cdac.repositories;

import com.cdac.entities.Category;
import com.cdac.entities.Post;
import com.cdac.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findAllByUser(User user);

    List<Post> findAllByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
