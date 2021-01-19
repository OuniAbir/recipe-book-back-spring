package com.recipesbook.Controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Domain.Recipe;
import com.recipesbook.Repository.RecipeRepository;

@RestController
@RequestMapping("/recipes")
@CrossOrigin("http://localhost:4200")
public class RecipeController {

	@Autowired
	private RecipeRepository recipeRepository;

	@GetMapping("/")
	@ResponseBody
	public Set<Recipe> findAll() {
		Optional<Set<Recipe>> result = recipeRepository.findAllRecipes();
		Set<Recipe> recipes;
		if (result.isPresent()) {recipes = result.get();} else {throw new RuntimeException("Did not find recipe ");};
		return recipes;

	}

	@GetMapping("/findRecipeById")
	@ResponseBody
	public Recipe findRecipeById(@RequestParam(value = "recipeId")  Long recipeId ) {
		Optional<Recipe> result = recipeRepository.findRecipeById(recipeId) ;
		Recipe theRecipe ;
		if (result.isPresent()) { theRecipe = result.get();} else { throw new RuntimeException("Did not find Recipe with id : "+recipeId ); } ;
		return theRecipe ;
	}
	
	
	@GetMapping("/findByCategoryId")
	@ResponseBody
	public Set<Recipe> findByCategoryid(@RequestParam(value = "categoryId", required = true) Long categoryId) {
		Optional<Set<Recipe>> result = recipeRepository.findByCategoryId(categoryId);
		Set<Recipe> recipes;
		if (result.isPresent()) {recipes = result.get();} else {throw new RuntimeException("Did not find recipe with category Id - " + categoryId);};
		return recipes;

	}

	@GetMapping("/findBySearchName")
	@ResponseBody
	public Set<Recipe> findBySearchName(
			@RequestParam(value = "name", required = true) String name) {
		Optional<Set<Recipe>> result = recipeRepository.findByNameContaining(name);
		Set<Recipe> recipes;
		if (result.isPresent()) {recipes = result.get();} else {throw new RuntimeException("Did not find recipe with name containing - " + name);};
		return recipes;
	}

}
