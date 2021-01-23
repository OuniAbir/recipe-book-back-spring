package com.recipesbook.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recipesbook.Domain.Category;
import com.recipesbook.Domain.Recipe;
import com.recipesbook.Domain.User;
import com.recipesbook.Dto.RecipeRequest;
import com.recipesbook.Dto.RecipeResponse;
 

@Mapper(componentModel = "spring")
public interface RecipeMapper {


	@Mapping(target = "id", source = "recipeRequest.id")
	@Mapping(target = "name", source = "recipeRequest.name")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(source = "category" , target = "category")
	@Mapping(source = "user" , target = "user")
	Recipe mapRecipeRequestToRecipeEntity(RecipeRequest recipeRequest,Category category, User user);
	
	
    
	@Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "userName", source = "user.username")
	RecipeResponse mapRecipeEntityToRecipeResponse(Recipe recipe);
	
}
