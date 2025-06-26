package org.example.tacoonline.data;

import org.example.tacoonline.model.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
}
