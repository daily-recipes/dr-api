package io.codextension.dr.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode()
public class Ingredient {

    private String name;
    private MeasurementUnit unit;
    private String notes;
    @JsonIgnore
    private List<Recipe> ingredientOf;
}