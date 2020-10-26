package io.codextension.dr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@ApiModel
@Data
@EqualsAndHashCode()
public class Recipe {

    @ApiModelProperty(position = 1, required = true, name = "name")
    private String name;
    private int duration;
    private String notes;
    private List<String> tags;
    private List<String> instructions;
    private List<Ingredient> ingredients;
    private String imageUrl;
    private int servings;
    @JsonIgnore
    private Family belongsTo;
}