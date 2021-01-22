package com.recipesbook.Service;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipesbook.Domain.Category;
import com.recipesbook.Dto.CategoryDto;
import com.recipesbook.Exception.RecipeBookException;
import com.recipesbook.Mapper.CategoryMapper;
import com.recipesbook.Repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Transactional
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		Category newCategory = categoryRepository.save(categoryMapper.mapDtoToEntityCategory(categoryDto));
		categoryDto.setId(newCategory.getId());
		return categoryDto;
	}

	@Transactional(readOnly = true)
	public Set<CategoryDto> findAllCategory() {
		return categoryRepository.findAll().stream().map(categoryMapper::mapEntityToDtoCategory).collect(toSet());
	}

	public CategoryDto getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RecipeBookException("Category not found with id " + id));
		return categoryMapper.mapEntityToDtoCategory(category);
	}
}
