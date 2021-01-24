package com.recipesbook.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

	private String authenticationToken ;
	private String username ;
	
	
    private Instant expiresAt;
    private String refreshToken;

}
