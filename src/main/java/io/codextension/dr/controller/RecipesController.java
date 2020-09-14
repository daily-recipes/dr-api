package io.codextension.dr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.codextension.dr.model.Recipe;
import io.codextension.dr.repository.RecipeRepository;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipesController {

    @Autowired
    private RecipeRepository recipeRepository;
    
    @GetMapping(value = "", produces = "application/json")
    public List<Recipe> getAllRecipesByMember(Principal principal) throws ChangeSetPersister.NotFoundException {
        List<Recipe> allRecipes = recipeRepository.findRecipesByFamilyMember(principal.getName());
        if(allRecipes.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return allRecipes;
    }
}