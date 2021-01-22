package com.recipesbook.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "vote")
public class Vote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "likes")
	private int likes ; 
	
	@Column(name = "dislikes")
    private int dislikes;
    
    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe ;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    
}
