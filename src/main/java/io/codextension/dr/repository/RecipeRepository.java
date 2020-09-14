package io.codextension.dr.repository;

import io.codextension.dr.model.Recipe;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    public List<Recipe> findRecipesByFamilyMember(String memberId);
}
