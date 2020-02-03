package com.service;

import com.model.Recipe;
import com.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public List<Recipe> findByAuthenticated(boolean auth){
        return recipeRepository.findByAuthenticated(auth);
    }

    public Recipe findById(Long id){
        return recipeRepository.findById(id).get();
    }
}
