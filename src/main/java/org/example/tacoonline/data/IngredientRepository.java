package org.example.tacoonline.data;

import org.example.tacoonline.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
