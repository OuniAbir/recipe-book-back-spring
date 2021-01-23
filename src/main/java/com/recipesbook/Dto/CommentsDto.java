package com.recipesbook.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

    private Long id;
    private String text;
    private Instant createdDate;

    

    private Long recipeId ;   
    private String userName ;
	
}
