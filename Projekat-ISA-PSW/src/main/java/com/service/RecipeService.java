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

    public Recipe authRecipe(Recipe r){
        Recipe recipe = new Recipe().builder()
                .authenticated(true)
                .nurse(r.getNurse())
                .appointment(r.getAppointment())
                .id(r.getId())
                .description(r.getDescription())
                .drug(r.getDrug()).build();

        Recipe ret = recipeRepository.save(recipe);
        return ret;
    }
}
