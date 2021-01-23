package com.recipesbook.Controller;

import static org.springframework.http.ResponseEntity.status;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Dto.CommentsDto;
import com.recipesbook.Service.CommentsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

	private final CommentsService commentsService;
	
	@PostMapping()
	public ResponseEntity<Void> createComment(@RequestBody CommentsDto CommentsDto ){

		commentsService.createComment(CommentsDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("by-recipe/{recipeId}")
	public ResponseEntity<Set<CommentsDto>> findAllCommentForRecipe(@PathVariable Long recipeId ){
		return status(HttpStatus.OK)
				.body(commentsService.findAllCommentForRecipe(recipeId)) ;
	}
	
	
	@GetMapping("by-user/{userName}")
	public ResponseEntity<Set<CommentsDto>> getCommentsByUsername( @PathVariable String  userName){
		
		return status(HttpStatus.OK).body(commentsService.findCommentsByUsername(userName)) ;
		
	}
}
