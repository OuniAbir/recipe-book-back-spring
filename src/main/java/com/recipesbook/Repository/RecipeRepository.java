package com.recipesbook.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipesbook.Domain.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	
	@Query( value = "SELECT * FROM Recipe WHERE category_id = :id" , nativeQuery = true )
	public Set<Recipe> findByCategoryId(@Param("id") Long id );
	
	@Query( value = "SELECT * FROM Recipe WHERE lower(name) like lower( concat('%', :name  , '%'))" , nativeQuery = true )
	public Set<Recipe>  findByNameContaining(@Param("name") String name );
	

}
