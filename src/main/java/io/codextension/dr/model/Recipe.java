package io.codextension.dr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@Table(name = "recipe")
@Data
@EqualsAndHashCode(callSuper = true)
@NamedQuery(name = "Recipe.findRecipesByFamilyMember", query = "select r from Recipe r join r.belongsTo f join f.members m where m.id= :memberId")
@NamedQuery(name = "Recipe.findRecipeByFamilyMember", query = "select r from Recipe r join r.belongsTo f join f.members m where m.id= :memberId and r.id= :recipeId")
public class Recipe extends AbstractPersistable<Long> {

  private String name;

  private int duration;

  private String notes;

  @ElementCollection(targetClass = String.class)
  private List<String> tags;

  @ElementCollection(targetClass = String.class)
  private List<String> instructions;

  @ManyToMany
  @JoinTable(name = "RECIPE_INGREDIENT", joinColumns = @JoinColumn(name = "RECIPE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "INGREDIENT_ID", referencedColumnName = "ID"))
  private List<Ingredient> ingredients;

  private String imageUrl;

  private int servings;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "family_id", referencedColumnName = "id")
  private Family belongsTo;

  @Setter(value = AccessLevel.NONE)
  @Version
  @Column(name = "VERSIONNB")
  private int version;
}