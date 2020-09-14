package io.codextension.dr.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "RECIPE_INGREDIENT")
public class RecipeIngredientDetails {
    @EmbeddedId
    private RecipeIngredientId id;
    private int quantity;
}