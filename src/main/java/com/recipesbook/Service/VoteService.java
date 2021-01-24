package com.recipesbook.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipesbook.Domain.Recipe;
import com.recipesbook.Domain.Vote;
import com.recipesbook.Dto.VoteDto;
import com.recipesbook.Exception.RecipeBookException;
import com.recipesbook.Repository.RecipeRepository;
import com.recipesbook.Repository.VoteRepository;

import lombok.AllArgsConstructor;
import static com.recipesbook.Domain.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

	private final VoteRepository voteRepository;
	private final RecipeRepository recipeRepository;
	private final AuthService authService;

	public void vote(VoteDto voteDto) {
		Recipe recipe = recipeRepository.findById(voteDto.getRecipeId())
				.orElseThrow(() -> new RecipeBookException("no recipe found with this id : " + voteDto.getRecipeId()));
		/* retrieve the recent Vote submitted by the currently logged-in user for the given recipe*/
		
		System.out.println("<<<<<<<<<<<<<<<<<< found recipe to vote"+ recipe.toString());
		Optional<Vote> voteByRecipeAndUser = voteRepository.findTopByRecipeAndUserOrderByIdDesc(recipe,
				authService.getCurrentUser());

		/* check whether the user has already performed the same Vote action or not */
		if (voteByRecipeAndUser.isPresent() && voteByRecipeAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
			throw new RecipeBookException("you have already " + voteDto.getVoteType() + " for this recipe ");
		}

		if (UPVOTE.equals(voteDto.getVoteType())) {
			recipe.setVoteCount(recipe.getVoteCount() + 1);
		} else {
			recipe.setVoteCount(recipe.getVoteCount() - 1);
		}

		voteRepository.save(mapVoteDtoToVote(voteDto, recipe));
		recipeRepository.save(recipe);

	}

	private Vote mapVoteDtoToVote(VoteDto voteDto, Recipe recipe) {
		return Vote.builder().voteType(voteDto.getVoteType()).recipe(recipe).user(authService.getCurrentUser()).build();
	}

}
