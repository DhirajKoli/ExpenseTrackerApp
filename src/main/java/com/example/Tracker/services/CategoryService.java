package com.example.Tracker.services;

import com.example.Tracker.dto.CategoryDto;
import com.example.Tracker.dto.SubCategoryDto;
import com.example.Tracker.model.Account;
import com.example.Tracker.model.Category;
import com.example.Tracker.model.SubCategory;
import com.example.Tracker.repository.CategoryRepository;
import com.example.Tracker.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public Category postCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public Category getCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent())
            return optionalCategory.get();
        throw new RuntimeException("Category Not Found!!");
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public SubCategory postSubCategory(SubCategoryDto subCategoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(subCategoryDto.getCategoryId());
        Category category = null;
        if(optionalCategory.isPresent())
            category = optionalCategory.get();
        else
            throw new RuntimeException("Category Not Found!!");
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryDto.getName());
        category.addSubcategory(subCategory);
        categoryRepository.save(category);
        return subCategory;
    }

    public SubCategory getSubCategory(Long subCategoryId) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subCategoryId);
        if(optionalSubCategory.isPresent())
            return optionalSubCategory.get();
        throw new RuntimeException("Category Not Found!!");
    }

    public List<SubCategory> getAllSubCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent())
            return optionalCategory.get().getSubCategories();
        throw new RuntimeException("Category Not Found!!");
    }
}
