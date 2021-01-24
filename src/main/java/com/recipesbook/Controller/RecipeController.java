package com.recipesbook.Controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Dto.RecipeRequest;
import com.recipesbook.Dto.RecipeResponse;
import com.recipesbook.Service.RecipeService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/recipes/")
@AllArgsConstructor
public class RecipeController {

	private final RecipeService recipeService;

	@GetMapping
	public ResponseEntity<Set<RecipeResponse>> findAll() {
		return status(HttpStatus.OK).body(recipeService.findAll());

	}

	@GetMapping("{recipeId}")
	public ResponseEntity<RecipeResponse> findRecipeById(@PathVariable Long recipeId) {

		return status(HttpStatus.OK).body(recipeService.findRecipeById(recipeId));
	}

	@GetMapping("by-category/{categoryid}")
	public ResponseEntity<Set<RecipeResponse>> findByCategoryid(@PathVariable Long categoryid) {

		return status(HttpStatus.OK).body(recipeService.findByCategoryid(categoryid));

	}

	@GetMapping("by-name/{recipeName}")
	public ResponseEntity<Set<RecipeResponse>> findBySearchName(@PathVariable String recipeName) {

		return status(HttpStatus.OK).body(recipeService.findBySearchName(recipeName));
	}
	
	@PostMapping
	public ResponseEntity<Void> createRecipe(@RequestBody RecipeRequest recipeRequest) {
		recipeService.save(recipeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	
	@GetMapping("by-user/{userName}")
	public ResponseEntity<Set<RecipeResponse>> getByUserName(@PathVariable String userName){

		return status(HttpStatus.OK).body(recipeService.findbyUserName(userName));
		
	}

}
