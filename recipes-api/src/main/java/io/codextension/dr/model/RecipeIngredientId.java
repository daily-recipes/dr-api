package io.codextension.dr.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RecipeIngredientId implements Serializable {
    private static final long serialVersionUID = -4377592959507173512L;

    @NotNull
    private Long recipeId;
    @NotNull
    private Long ingredientId;
}