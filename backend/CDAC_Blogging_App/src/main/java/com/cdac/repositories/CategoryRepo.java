package com.cdac.repositories;

import com.cdac.dtos.CategoryDTO;
import com.cdac.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<CategoryDTO> findByName(String name);

}
