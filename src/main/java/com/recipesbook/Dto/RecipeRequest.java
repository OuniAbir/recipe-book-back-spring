package com.recipesbook.Dto;


import java.util.ArrayList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* the dto of recipe to save */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {
	
	private Long id;
	
	private String name ;
	private String source ;
	private int preptime ;
	private int waittime ;
	private int cooktime ;
	private int servings ;
	private int comments ;
	private int calories ;
	private int fat ;
	private int satfat ;
	private int carbs ;
	private int fiber ;
	private int sugar ;
	private int protein ;
	private String instructions ;
	private ArrayList<String> ingredients;
	private String imageUrl ;
	
	private String categoryName ;

}
