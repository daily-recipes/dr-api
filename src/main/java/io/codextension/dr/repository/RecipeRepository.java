package io.codextension.dr.repository;

import io.codextension.dr.model.Recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    public List<Recipe> findRecipesByFamilyMember(@Param("memberId") String memberId);

    public Optional<Recipe> findRecipeByFamilyMember(@Param("memberId") String memberId, @Param("recipeId") Long recipeId);
}
