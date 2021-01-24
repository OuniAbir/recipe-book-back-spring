package com.recipesbook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipesbook.Domain.Recipe;
import com.recipesbook.Domain.User;
import com.recipesbook.Domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByRecipeAndUserOrderByIdDesc(Recipe recipe, User currentUser);

}
