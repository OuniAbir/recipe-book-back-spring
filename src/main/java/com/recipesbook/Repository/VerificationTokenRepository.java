package com.recipesbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipesbook.Domain.VerificationToken;

import java.util.Optional;
 

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	@Query(value = " SELECT * FROM verification_token where  token = :token", nativeQuery = true )
	public Optional<VerificationToken> findByToken(@Param( value = "token") String token);
 
}
