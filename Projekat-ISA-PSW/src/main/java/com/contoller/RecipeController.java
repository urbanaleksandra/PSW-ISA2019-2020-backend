package com.contoller;

import com.dto.RecipeDTO;
import com.model.Drug;
import com.model.Nurse;
import com.model.Recipe;
import com.repository.MedicalStaffRepository;
import com.service.DrugService;
import com.service.MedicalStaffService;
import com.service.NurseService;
import com.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MedicalStaffService medicalStaffService;

    @Autowired
    private DrugService drugService;


    @RequestMapping(method = RequestMethod.GET, value = "/api/get-recipes")
    public List<Recipe> getRecipes(){

        List<Recipe> recipes = recipeService.findByAuthenticated(false);
        return recipes;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/get-drug/{id}")
    public Drug getDrug(@PathVariable Long id){
        System.out.println("usao u get-drug");
        Drug drug = new Drug();
        Optional<Recipe> recipes = recipeService.findById(id);
        //drug = getRecipes().get(0).getDrug();

        return drug;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/auth-recipe/{username}")
    public void authRecipe(@RequestBody RecipeDTO recipeDTO, @PathVariable String username){
        Nurse nurse = (Nurse) medicalStaffService.findByUsername(username);

        Optional<Recipe> recipe = recipeService.findById(recipeDTO.getId());
        Recipe r = recipe.get();
        r.setAuthenticated(true);
        r.setNurse(nurse);


        Recipe r1 = recipeService.save(r);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/get-drugs", method = RequestMethod.GET)
    public List<Drug> getDrugs(){
        return drugService.findAll();
    }
}
