package com.recipesbook.Repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipesbook.Domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
 	@Query( value = "SELECT * FROM Category  " , nativeQuery = true )
	Optional<Set<Category>> findAllCategory();
}
