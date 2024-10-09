package com.example.ECommerce.service.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ECommerce.dto.CategoryDto;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryRepository categoryRepository;

	@Override
	public Category createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
}

