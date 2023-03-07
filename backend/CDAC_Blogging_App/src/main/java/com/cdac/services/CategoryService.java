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

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Category insertCategory(CategoryDTO categoryDTO) {
        Category category = dtoToCategory(categoryDTO);
        category = categoryRepo.save(category);

        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty()) throw new ResourceNotFoundException("Category not found with id: " + id);

        return optionalCategory.get();
    }

    public Category updateCategory(long id, CategoryDTO updatedCategoryDTO) {
        Category category = getCategoryById(id);
        category = dtoToCategory(updatedCategoryDTO);
        category.setId(id);

        return categoryRepo.save(category);
    }

    public Category deleteCategory(long id) {
        Category category = getCategoryById(id);
        categoryRepo.deleteById(id);

        return category;
    }

    public CategoryDTO categoryToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Category dtoToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

}
