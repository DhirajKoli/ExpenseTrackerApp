package com.example.Tracker.repository;

import com.example.Tracker.model.Category;
import com.example.Tracker.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
