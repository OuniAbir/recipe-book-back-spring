package com.recipesbook.Domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name ;
	
	@Column(name = "source")
	private String source ;
		
	@Column(name = "preptime")
	private int preptime ;

	@Column(name = "cooktime")
	private int cooktime ;

	
	@Column(name = "servings")
	private int servings;
	
	@Column(name = "calories")
	private int calories;
	
	@Column(name = "comments")
	private String comments ;
	
	
	@Column(name = "instructions")
	private String instructions ;
	
	@Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
	private Difficulty difficulty ;
	
	@Column(name = "image_url")
	private String imageUrl ;
   
	/* many recipes to one category bidirectional*/
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	/* one recipe to many categories unidirectional*/
	@OneToMany(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
	@JoinColumn(name = "recipe_id")
	private Set<Ingredient> ingredients; 
		
	public void addIngredient(Ingredient ingredient) {
		
		if (this.ingredients == null ) { this.ingredients = new HashSet<Ingredient>(); }
		
		ingredients.add(ingredient);
 	}
	
}
