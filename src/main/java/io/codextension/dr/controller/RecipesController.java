package io.codextension.dr.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.codextension.dr.model.Recipe;
import io.codextension.dr.repository.RecipeRepository;

@RestController
@CrossOrigin
public class RecipesController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping(value = "/recipes/{memberId}", produces = "application/json", consumes = "application/json")
    public List<Recipe> getAllRecipesForMember(@NotNull @PathVariable(required = true) String memberId)
            throws NotFoundException {
        List<Recipe> allRecipes = recipeRepository.findRecipesByFamilyMember(memberId);
        if (allRecipes.isEmpty()) {
            throw new NotFoundException();
        }
        return allRecipes;
    }

    @GetMapping(value = "/recipes/{memberId}/{recipeId}", produces = "application/json", consumes = "application/json")
    public Recipe getRecipeForMember(@NotNull @PathVariable(required = true) String memberId,
            @NotNull @PathVariable(required = true) Long recipeId) throws NotFoundException {
        Optional<Recipe> recipe = recipeRepository.findRecipeByFamilyMember(memberId, recipeId);
        if (recipe.isPresent())
            return recipe.get();
        else
            throw new NotFoundException();
    }
}