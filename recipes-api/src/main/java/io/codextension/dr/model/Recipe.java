package io.codextension.dr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Saving recipe:
 *
 * merge(r:Recipe {id:"${entity.id}"}) ON CREATE SET r.id="${entity.id}", r.name="${entity.name}", r.imageUrl="${entity.imageUrl}", r.favourite=${entity.favourite}, r.notes="${entity.notes ||
 *             ""}", r.duration=${entity.duration}, r.servings=${entity.servings}, ${tags}, ${instructions} ON MATCH SET r.favourite=${entity.favourite}, r.servings=${entity.servings}, r.duration=${entity.duration}, r.notes="${entity.notes ||
 *             ""}", r.imageUrl="${entity.imageUrl}", ${tags}, ${instructions}, r.name="${entity.name}" return r.id
 *
 * `match (:Recipe {id:"${entity.id}"})-[c:CONTAINS]->(:Ingredient) delete c`
 *
 * `merge(i:Ingredient {name:"${ingredient.name}"}) ON CREATE SET i.id="${ingredient.id}", i.name="${ingredient.name}" ON MATCH SET i.name="${ingredient.name.trim()}" return i.id`
 *
 * `match (r:Recipe {id:"${entity.id}"}) match(i:Ingredient {name:"${ingredient.name}"}) create (r)-[:CONTAINS {${qty} unit:"${unit}" ${notes}}]->(i)`
 *
 * ==
 *
 *
 */
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
    private List<String> ingredients;
    private String imageUrl;
    private int servings;
}