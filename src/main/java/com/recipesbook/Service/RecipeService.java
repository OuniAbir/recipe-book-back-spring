package com.recipesbook.Service;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.recipesbook.Domain.Category;
import com.recipesbook.Domain.Recipe;
import com.recipesbook.Dto.RecipeRequest;
import com.recipesbook.Dto.RecipeResponse;
import com.recipesbook.Exception.RecipeBookException;
import com.recipesbook.Mapper.RecipeMapper;
import com.recipesbook.Repository.CategoryRepository;
import com.recipesbook.Repository.RecipeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeService {

	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
    private final AuthService authService;
	private final RecipeMapper recipeMapper;

	public Set<RecipeResponse> findAll() {
		return recipeRepository.findAll().stream().map(recipeMapper::mapRecipeEntityToRecipeResponse).collect(toSet());
	}

	public RecipeResponse findRecipeById(Long recipeId) {
		Recipe recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new RecipeBookException("Recipe not found with id - " + recipeId));
		return recipeMapper.mapRecipeEntityToRecipeResponse(recipe);
	}

	public Set<RecipeResponse> findByCategoryid(Long categoryId) {
		return recipeRepository.findByCategoryId(categoryId).stream().map(recipeMapper::mapRecipeEntityToRecipeResponse)
				.collect(toSet());
	}

	public Set<RecipeResponse> findBySearchName(String name) {
		return recipeRepository.findByNameContaining(name).stream().map(recipeMapper::mapRecipeEntityToRecipeResponse)
				.collect(toSet());

	}
	
	public void save(RecipeRequest recipeRequest) {
        Category Category = categoryRepository.findByName(recipeRequest.getCategoryName())
                .orElseThrow(() -> new RecipeBookException("No category found with this name "+ recipeRequest.getCategoryName()));      
        recipeRepository.save(recipeMapper.mapRecipeRequestToRecipeEntity(recipeRequest, Category, authService.getCurrentUser()));
    }

}
