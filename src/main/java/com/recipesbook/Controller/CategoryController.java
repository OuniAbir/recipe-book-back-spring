package com.recipesbook.Controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Domain.Category;
import com.recipesbook.Repository.CategoryRepository;

@RestController
@RequestMapping("/recipe-category")
@CrossOrigin("http://localhost:4200")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository ;
	
	@GetMapping("/")
	@ResponseBody
	Set<Category> findAllCategory() {
		Optional<Set<Category>> result = categoryRepository.findAllCategory();
		Set<Category> categories;
		if (result.isPresent()) { categories = result.get(); } else { throw new RuntimeException("Did not find recipe ");}
		return categories;
	}

}
