package com.recipesbook.util;

import lombok.experimental.UtilityClass;

/* lombok anotation, at compile time it will mark class as final, set all method/fields to static, generates a private no-arg constructor. */
@UtilityClass 
public class Constants {
	
	public static final String ACTIVATION_EMAIL = "http://localhost:8080/api/auth/accountVerification" ;
}
