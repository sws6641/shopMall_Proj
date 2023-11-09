package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.CategoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, CategoryId> {
    List<Category> findAll();
}
