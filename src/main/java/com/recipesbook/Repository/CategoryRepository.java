package com.recipesbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.recipesbook.Domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
