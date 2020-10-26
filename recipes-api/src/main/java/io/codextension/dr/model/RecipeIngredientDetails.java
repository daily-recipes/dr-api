package io.codextension.dr.model;

import lombok.Data;

@Data
public class RecipeIngredientDetails {
    private RecipeIngredientId id;
    private int quantity;
}