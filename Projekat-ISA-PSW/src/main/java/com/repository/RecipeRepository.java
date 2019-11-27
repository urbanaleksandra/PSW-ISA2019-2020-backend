package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	
	Optional<Recipe> findById(Long id);
	

}
