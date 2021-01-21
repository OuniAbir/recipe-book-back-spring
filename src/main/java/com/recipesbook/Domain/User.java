package com.recipesbook.Domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "username")
    @NotBlank(message = "Username is required")
	private String username ;
    
	@Column(name = "password")
    @NotBlank(message=" password is required ")
	private String password ;

	@Column(name = "email")
	private String email ;

	@Column(name = "created")
	private Instant  created ;

	@Column(name = "enabled")
	private boolean enabled ;
	
	
		
	
	
}
