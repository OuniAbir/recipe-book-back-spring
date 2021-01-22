package com.recipesbook.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class CategoryDto {

	private Long id;
	private String name;
	private int recipeCount ;
}
