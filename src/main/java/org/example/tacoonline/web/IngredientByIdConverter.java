package org.example.tacoonline.web;

import lombok.RequiredArgsConstructor;
import org.example.tacoonline.data.IngredientRepository;
import org.example.tacoonline.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;


    @Override
    public Ingredient convert(String source) {
        return ingredientRepository.findOne(source);
    }
}
