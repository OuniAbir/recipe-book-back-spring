package com.recipesbook.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipesbook.Domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query( value = "SELECT * FROM comment WHERE recipe_id = :recipeId" , nativeQuery = true )
	public Set<Comment> findByRecipeId(@Param("recipeId") Long recipeId);

	@Query( value = "SELECT * FROM comment WHERE user_id = :userId" , nativeQuery = true )
	public Set<Comment> findCommentsByUseId(@Param("userId")Long userId);
	
	

}
