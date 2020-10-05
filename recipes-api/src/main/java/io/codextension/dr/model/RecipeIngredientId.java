package io.codextension.dr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Embeddable
@Data
public class RecipeIngredientId implements Serializable {
    private static final long serialVersionUID = -4377592959507173512L;

    @NotNull
    @Column(name="RECIPE_ID")
    private Long recipeId;

    @NotNull
    @Column(name="INGREDIENT_ID")
    private Long ingredientId;
}