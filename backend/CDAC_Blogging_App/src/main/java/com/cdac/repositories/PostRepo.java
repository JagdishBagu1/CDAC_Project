package com.cdac.repositories;

import com.cdac.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    Optional<List<Post>> findAllByUser(long id);

    Optional<List<Post>> findAllByCategory(long id);

}
