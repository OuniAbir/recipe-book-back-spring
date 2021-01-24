package com.recipesbook.Domain;
 
import java.time.Instant;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name ;
	private String source ;
	private int preptime ;
	private int waittime ;
	private int cooktime ;
	private int servings ;
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

    private Instant createdDate;
	
	/* many recipes to one category bidirectional*/
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	

	/* many recipes to one user */
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user ;
	

    private int voteCount;
		
	
}
