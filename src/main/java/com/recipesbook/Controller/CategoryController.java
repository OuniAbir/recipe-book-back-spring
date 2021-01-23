package com.recipesbook.Controller;

import java.util.Set;
import static org.springframework.http.ResponseEntity.status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Dto.CategoryDto;
import com.recipesbook.Service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recipe-category/")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Set<CategoryDto>> findAllCategory() {
		return status(HttpStatus.OK).body(categoryService.findAllCategory());
	}

	@GetMapping("{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {

		return status(HttpStatus.OK).body(categoryService.getCategoryById(categoryId));
	}

	@PostMapping
	public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {

		return status(HttpStatus.CREATED).body(categoryService.saveCategory(categoryDto));
	}

}
