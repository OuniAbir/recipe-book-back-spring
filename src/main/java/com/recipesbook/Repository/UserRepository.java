package com.recipesbook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipesbook.Domain.User;
 
public interface UserRepository extends JpaRepository<User, Long> {

 	@Query( value = "SELECT * FROM recipe_book_user WHERE  username= :username " , nativeQuery = true )
	Optional<User> findByUserName(@Param( value = "username") String username);
 

}
