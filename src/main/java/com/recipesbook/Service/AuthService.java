package com.recipesbook.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipesbook.Domain.NotificationEmail;
import com.recipesbook.Domain.User;
import com.recipesbook.Domain.VerificationToken;
import com.recipesbook.Dto.RegisterRequest;
import com.recipesbook.Exception.RecipeBookException;
import com.recipesbook.Repository.UserRepository;
import com.recipesbook.Repository.VerificationTokenRepository;
import com.recipesbook.util.Constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository  ;
	 
    private final MailContentBuilder mailContentBuilder;
	private final MailService mailService ;

	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepository.save(user);
		
		 String token = generateVerificationToken(user);
		 /* we have a token, now let's send an email that contains this verification token. */
		 String message = mailContentBuilder.Build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : " + Constants.ACTIVATION_EMAIL + "/" + token) ;
		 mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));
	}

	
	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token ;
 	}
	private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
		verificationTokenOptional.orElseThrow(() -> new RecipeBookException("Invalid Token "));
		fetchUserAndEnable(verificationTokenOptional.get());
		
	}
	
	
 	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUserName(username).orElseThrow(()->  new RecipeBookException("User not Found with id - " + username));
		user.setEnabled(true);
		userRepository.save(user);
		
	}

}
