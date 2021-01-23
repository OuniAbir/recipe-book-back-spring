package com.recipesbook.Controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Dto.AuthenticationResponse;
import com.recipesbook.Dto.LoginRequest;
import com.recipesbook.Dto.RegisterRequest;
import com.recipesbook.Service.AuthService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

	private final  AuthService authService ;
	
	@PostMapping("signup")
	public ResponseEntity<RegisterRequest> signup(@RequestBody RegisterRequest registerRequest ) { 
		
		authService.signup(registerRequest);
		return new ResponseEntity<RegisterRequest>(OK);
	}
	
	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successully", OK);
	}
	
	@PostMapping("login")
	public AuthenticationResponse  login(@RequestBody LoginRequest LoginRequest )
	{
		return authService.login(LoginRequest);
	}
	
}
