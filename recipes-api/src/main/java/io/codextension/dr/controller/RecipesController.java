package io.codextension.dr.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.codextension.dr.model.Recipe;
import io.codextension.dr.repository.RecipeRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.*;

@Api(tags = "Recipes", description = "Information related to the recipes", produces = "application/json", protocols = "https,http", authorizations = @Authorization(value = "", scopes = {
        @AuthorizationScope(scope = "read", description = "lalala") }))
@RestController
@CrossOrigin
public class RecipesController {

    @Autowired
    private RecipeRepository recipeRepository;

    @ApiOperation(value = "getAllRecipesForMember", notes = "Get all recipes for a member", nickname = "getAllRecipesForMember")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = Recipe.class, responseContainer = "List") })
    @GetMapping(value = "/recipes/chef/{memberId}", produces = "application/json", consumes = "application/json")
    public List<Recipe> getAllRecipesForMember(
            @NotNull @PathVariable(required = true) @ApiParam(value = "memberId", required = true) String memberId)
            throws NotFoundException {
        List<Recipe> allRecipes = recipeRepository.findRecipesByFamilyMember(memberId);
        if (allRecipes.isEmpty()) {
            throw new NotFoundException();
        }
        return allRecipes;
    }

    @DeleteMapping(value = "/recipes/{recipeId}/chef/{memberId}", produces = "application/json", consumes = "application/json")
    public boolean deleteRecipeForMember(@NotNull @PathVariable(required = true) String memberId,
            @NotNull @PathVariable(required = true) Long recipeId) throws NotFoundException {
        // delete by id and owner
        return true;
    }

    @PutMapping(value = "/recipes/{recipeId}/chef/{memberId}", produces = "application/json", consumes = "application/json")
    public Recipe updateRecipeForMember(@NotNull @PathVariable(required = true) String memberId,
            @NotNull @PathVariable(required = true) Long recipeId) throws NotFoundException {
        return null;
    }

    @GetMapping(value = "/recipes/{recipeId}/chef/{memberId}", produces = "application/json", consumes = "application/json")
    public Recipe getRecipeForMember(@NotNull @PathVariable(required = true) String memberId,
            @NotNull @PathVariable(required = true) Long recipeId) throws NotFoundException {
        Optional<Recipe> recipe = recipeRepository.findRecipeByFamilyMember(memberId, recipeId);
        if (recipe.isPresent())
            return recipe.get();
        else
            throw new NotFoundException();
    }
}