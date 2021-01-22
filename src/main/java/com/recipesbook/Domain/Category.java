package com.recipesbook.Domain;

 import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
 
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "name")
	private String name ;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL, mappedBy = "category")
 	private Set<Recipe> recipes ;
	

	
}