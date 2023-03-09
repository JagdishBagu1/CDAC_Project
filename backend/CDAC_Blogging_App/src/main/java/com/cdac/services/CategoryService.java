package com.cdac.services;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.CategoryDTO;
import com.cdac.entities.Category;
import com.cdac.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
        Category category = dtoToCategory(categoryDTO);
        category = categoryRepo.save(category);

        return categoryToDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream().map((category -> categoryToDTO(category))).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty()) throw new ResourceNotFoundException("Category not found with id: " + id);

        return categoryToDTO(optionalCategory.get());
    }

    public CategoryDTO updateCategory(long id, CategoryDTO updatedCategoryDTO) {
        getCategoryById(id);
        Category category = dtoToCategory(updatedCategoryDTO);
        category.setId(id);

        return categoryToDTO(categoryRepo.save(category));
    }

    public CategoryDTO deleteCategory(long id) {
        CategoryDTO categoryDTO = getCategoryById(id);
        categoryRepo.deleteById(id);

        return categoryDTO;
    }

    public CategoryDTO categoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category dtoToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

}
