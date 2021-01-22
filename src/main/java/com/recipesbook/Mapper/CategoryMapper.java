package com.recipesbook.Mapper;

import java.util.Set;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recipesbook.Domain.Category;
import com.recipesbook.Domain.Recipe;
import com.recipesbook.Dto.CategoryDto;

 
@Mapper(componentModel = "spring")
/* annotation to specify that this interface is a Mapstruct Mapper and Spring should identify it as a component  */
public interface CategoryMapper {
	
	/* mapping from recipes list in the entity to the field recipeCount in the dto */
	/* expresion is the logic that return the value for the target field */
	@Mapping(target = "recipeCount" ,  expression = "java(mapRecipes(category.getRecipes()))") 
	CategoryDto mapEntityToDtoCategory(Category category);
	
	default int mapRecipes(Set<Recipe> recipes ) {
		return recipes.size();			
	}
	
	/* @InheritInverseConfiguration annotation to reverse the mapping already existing to convert from dto to entity*/
	@InheritInverseConfiguration
	@Mapping(target = "recipes" , ignore = true )
	Category mapDtoToEntityCategory(CategoryDto categoryDto);

}
