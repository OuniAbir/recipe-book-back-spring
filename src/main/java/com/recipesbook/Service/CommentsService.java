package com.recipesbook.Service;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.recipesbook.Domain.Comment;
import com.recipesbook.Domain.NotificationEmail;
import com.recipesbook.Domain.Recipe;
import com.recipesbook.Domain.User;
import com.recipesbook.Dto.CommentsDto;
import com.recipesbook.Exception.RecipeBookException;
import com.recipesbook.Mapper.CommentMapper;
import com.recipesbook.Repository.CommentRepository;
import com.recipesbook.Repository.RecipeRepository;
import com.recipesbook.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentsService {
	private final CommentRepository commentRepository;
	private final CommentMapper commentMapper;
	private final AuthService authService;
	private final RecipeRepository recipeRepository;
	private final MailContentBuilder mailContentBuilder;
    private static final String POST_URL = "";
    private final MailService mailService;
    private final UserRepository  userRepository ;
    
	public void createComment(CommentsDto commentsDto) {
		Recipe recipe = recipeRepository.findById(commentsDto.getRecipeId()).orElseThrow(() -> new RecipeBookException("No recipe found with this id : " + commentsDto.getRecipeId())) ;
		User commentUser =  authService.getCurrentUser() ;
		User recipeUSer = recipe.getUser();
		
		System.out.println(">>>>>>>>>>>>>>>>> create Comment service : the comment created by : "+commentUser.getUsername());
		System.out.println(">>>>>>>>>>>>>>>>> create Comment service  : the comment created on recipe of : " + recipeUSer.getUsername());

		Comment comment = commentMapper.mapCommentDtoToCommentEntity(commentsDto, recipe ,commentUser);		
		commentRepository.save(comment) ;
		
		/* Send notification mail to the owner of the recipe about the comment */
		String message = mailContentBuilder.Build(commentUser.getUsername()+ " post a Comment on your recipe " + POST_URL); 
		mailService.sendMail(new NotificationEmail( commentUser.getUsername() +" Commented on your recipe", recipeUSer.getEmail(), message));

	    }


	public Set<CommentsDto> findAllCommentForRecipe(Long recipeId) {
		return commentRepository.findByRecipeId(recipeId)
				.stream()
				.map(commentMapper::mapCommentEntityToCommentDto)
				.collect(toSet()); 	
	}


	public Set<CommentsDto> findCommentsByUsername(String userName) {
		User user = userRepository.
				findByUserName(userName)
				.orElseThrow(() -> new RecipeBookException("No user found with this name : " + userName));
		return commentRepository.findCommentsByUseId(user.getId())
				.stream()
				.map(commentMapper::mapCommentEntityToCommentDto)
				.collect(toSet());

 	}
	 
	 
}
