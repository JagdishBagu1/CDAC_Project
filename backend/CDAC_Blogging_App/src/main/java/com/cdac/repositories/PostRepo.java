package com.cdac.repositories;

import com.cdac.dtos.PostDTO;
import com.cdac.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    Optional<List<PostDTO>> findAllByUser(long id);

    Optional<List<PostDTO>> findAllByCategory(long id);

}
