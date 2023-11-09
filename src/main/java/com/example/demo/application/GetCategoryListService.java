package com.example.demo.application;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCategoryListService {
    private final CategoryRepository categoryRepository;

    public GetCategoryListService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
