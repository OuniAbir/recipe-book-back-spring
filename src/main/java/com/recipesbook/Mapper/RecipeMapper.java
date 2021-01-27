package com.recipesbook.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.recipesbook.Domain.Category;
import com.recipesbook.Domain.Recipe;
import com.recipesbook.Domain.User;
import com.recipesbook.Domain.Vote;
import com.recipesbook.Domain.VoteType;
import com.recipesbook.Dto.RecipeRequest;
import com.recipesbook.Dto.RecipeResponse;
import com.recipesbook.Repository.CommentRepository;
import com.recipesbook.Repository.VoteRepository;
import com.recipesbook.Service.AuthService;

import static com.recipesbook.Domain.VoteType.DOWNVOTE; 
import static com.recipesbook.Domain.VoteType.UPVOTE;

import java.util.Optional;; 

@Mapper(componentModel = "spring")
/* public interface RecipeMapper { */

public abstract class RecipeMapper {
/* need to inject not final/static vars */
	@Autowired
	private AuthService authService  ;

    @Autowired
    private VoteRepository voteRepository;
    
    @Autowired
    private CommentRepository commentRepository ; 
    
    
	@Mapping(target = "id", source = "recipeRequest.id")
	@Mapping(target = "name", source = "recipeRequest.name")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(source = "category" , target = "category")
	@Mapping(source = "user" , target = "user")
    @Mapping(target = "voteCount", constant = "0")
	public abstract Recipe mapRecipeRequestToRecipeEntity(RecipeRequest recipeRequest,Category category, User user);
	
    @Mapping(target = "commentCount", expression = "java(commentCount(recipe))")
    @Mapping(target = "upVote", expression = "java(isRecipeUpVoted(recipe))")
    @Mapping(target = "downVote", expression = "java(isRecipeDownVoted(recipe))")
	@Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "userName", source = "user.username")
	public abstract RecipeResponse mapRecipeEntityToRecipeResponse(Recipe recipe);
	
    
    public boolean isRecipeUpVoted(Recipe recipe) {
    	return checkRecipeVote(recipe, UPVOTE);
    }
    
    public boolean isRecipeDownVoted(Recipe recipe) {
    	return checkRecipeVote(recipe, DOWNVOTE);
    }
    
    public boolean checkRecipeVote(Recipe recipe, VoteType voteType ){
    	
    	
    	if (authService.isLoggedIn()) {
    		/* only if the user logged in otherwire always return false */
    		
			Optional<Vote> voteForRecipeByUser = voteRepository.findTopByRecipeAndUserOrderByIdDesc(recipe, authService.getCurrentUser());
			/* in this stage vote is saved in DB */
			/* we want to send back a recipeResponse with
			 * if for example user did upvote, we send UPVOTE = false and DOWNVOTE=true  so the botton is frozen 
			 * and only the Downvote action is allowed */
			return voteForRecipeByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();				
    	}
    	/* user not loggedin , DOWNVOTE, UPVOTE both equal to false
    	 * user can't vote eveen if he can see the recipe  */
    	return false ;
    }
    
    public int commentCount(Recipe recipe) {
    	return commentRepository.findByRecipeId(recipe.getId()).size();
    }
}
