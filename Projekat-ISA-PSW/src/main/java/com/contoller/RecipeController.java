package com.contoller;

import com.dto.RecipeDTO;
import com.model.Appointment;
import com.model.Drug;
import com.model.Nurse;
import com.model.Recipe;
import com.repository.MedicalStaffRepository;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping(method = RequestMethod.GET, value = "/api/get-recipes")
    public List<Recipe> getRecipes(){

        List<Recipe> recipes = recipeService.findByAuthenticated(false);
        return recipes;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/get-recipes-dto")
    public List<RecipeDTO> getRecipesDTO(){
        List<Recipe> recipes = recipeService.findByAuthenticated(false);
        List<RecipeDTO> ret = new ArrayList<>();
        for (Recipe r : recipes){
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId(r.getId());
            recipeDTO.setDescription(r.getDescription());
            String drugs= "";
            for(Drug d:r.getDrug()){
                drugs += d.getName() + " | ";
            }
            recipeDTO.setDrugString(drugs);
            ret.add(recipeDTO);


        }
        return ret;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/get-drug/{id}")
    public Drug getDrug(@PathVariable Long id){
        System.out.println("usao u get-drug");
        Drug drug = new Drug();
        Recipe recipes = recipeService.findById(id);
        //drug = getRecipes().get(0).getDrug();

        return drug;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/auth-recipe/{username}")
    public @ResponseBody
    ResponseEntity authRecipe(@RequestBody RecipeDTO recipeDTO, @PathVariable String username){



        try {
            Recipe recipe = recipeService.authRecipe(recipeDTO, username);
            try {
                Appointment app = appointmentService.findById(recipe.getAppointment().getId());
                Appointment appointment = appointmentService.setFinished(app);
            }catch (Exception e){
                System.out.println("recept nema appointment");
            }
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/get-drugs", method = RequestMethod.GET)
    public List<Drug> getDrugs(){
        return drugService.findAll();
    }
}
