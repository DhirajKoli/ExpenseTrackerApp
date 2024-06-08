package com.example.Tracker.controller;

import com.example.Tracker.dto.CategoryDto;
import com.example.Tracker.dto.SubCategoryDto;
import com.example.Tracker.model.Category;
import com.example.Tracker.model.SubCategory;
import com.example.Tracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apps/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> postCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.postCategory(categoryDto), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @PostMapping("/subCategories")
    public ResponseEntity<SubCategory> postSubCategory(@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseEntity<>(categoryService.postSubCategory(subCategoryDto),HttpStatus.OK);
    }

    @GetMapping("/subCategories/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable("subCategoryId") Long subCategoryId){
        return new ResponseEntity<>(categoryService.getSubCategory(subCategoryId),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/subCategories")
    public ResponseEntity<List<SubCategory>> getAllSubCategory(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<>(categoryService.getAllSubCategory(categoryId),HttpStatus.OK);
    }
}
