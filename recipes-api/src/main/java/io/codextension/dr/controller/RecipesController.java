package io.codextension.dr.controller;

import io.codextension.dr.model.Recipe;
import io.swagger.annotations.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Api(tags = "Recipes", description = "Information related to the recipes", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@CrossOrigin
public class RecipesController {

    @Autowired
    private Driver driver;

    @ApiOperation(value = "GetRecipes", notes = "Get recipes filter by ingredient, or any additional properties", nickname = "GetRecipes")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = Recipe.class, responseContainer = "List")})
    @GetMapping(value = "/recipes}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getRecipes(@RequestParam(required = false, name = "m") @ApiParam(value = "memberId", required = true) String memberId)
            throws HttpClientErrorException.NotFound {
        try (Session session = driver.session()) {
            return session.run("MATCH (m:Movie) RETURN m ORDER BY m.name ASC")
                    .list(r -> r.get("m").asNode().asMap());
        }
    }

    @DeleteMapping(value = "/recipes/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteRecipe(@NotNull @PathVariable(required = true) Long recipeId) throws HttpClientErrorException.NotFound {
        // delete by id and owner
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/recipes/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe updateRecipe(@NotNull @PathVariable(required = true) Long recipeId) throws HttpClientErrorException.NotFound {
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/recipes/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe getRecipe(@NotNull @PathVariable(required = true) Long recipeId) throws HttpClientErrorException.NotFound {
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
}