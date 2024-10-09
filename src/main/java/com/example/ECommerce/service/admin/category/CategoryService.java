package com.example.ECommerce.service.admin.category;

import java.util.List;

import com.example.ECommerce.dto.CategoryDto;
import com.example.ECommerce.model.Category;

public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);
	List<Category> getAllCategories();
}
