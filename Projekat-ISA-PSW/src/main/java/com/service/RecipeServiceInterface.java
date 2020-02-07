package com.service;

import com.dto.RecipeDTO;
import com.model.Recipe;

import java.util.List;

public interface RecipeServiceInterface {

    public Recipe save(Recipe recipe);

    public List<Recipe> findByAuthenticated(boolean auth);

    public Recipe findById(Long id);

    public Recipe authRecipe(RecipeDTO r, String username);
}
