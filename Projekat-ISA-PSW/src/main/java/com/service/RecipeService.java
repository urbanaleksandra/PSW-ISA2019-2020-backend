package com.service;

import com.dto.RecipeDTO;
import com.model.Nurse;
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
    @Autowired
    private MedicalStaffService medicalStaffService;
    @Autowired
    private NurseService nurseService;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private EmailService emailService;

    public Recipe save(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public List<Recipe> findByAuthenticated(boolean auth){
        return recipeRepository.findByAuthenticated(auth);
    }

    public Recipe findById(Long id){
        return recipeRepository.findById(id).get();
    }


    @Transactional(rollbackFor = {RuntimeException.class}, readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Recipe authRecipe(RecipeDTO recipeDTO, String username){
        Recipe recipe = recipeService.findById(recipeDTO.getId());
        Nurse nurse = (Nurse) medicalStaffService.findByUsername(username);
        recipe.setNurse(nurse);
        System.out.println(recipe);

        recipe.setAuthenticated(true);
        try {
            emailService.sendPatientNotificaition9(nurse);
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }
        return recipeRepository.save(recipe);

    }
}
