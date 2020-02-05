package com.service;

import com.model.Recipe;
import com.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService implements RecipeServiceInterface{

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

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Recipe authRecipe(Recipe r){
        Recipe recipe = recipeRepository.findById(r.getId()).get();
        recipe.setAuthenticated(true);
        recipe.setNurse(r.getNurse());
        System.out.println(recipe);
        Recipe ret = recipeRepository.save(recipe);
        return ret;
    }
}
