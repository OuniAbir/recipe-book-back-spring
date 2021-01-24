package com.recipesbook.Dto;

import com.recipesbook.Domain.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteDto {

    private VoteType voteType;
    private Long RecipeId;
}
