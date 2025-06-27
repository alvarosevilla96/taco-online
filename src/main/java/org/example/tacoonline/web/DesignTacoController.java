package org.example.tacoonline.web;

import org.example.tacoonline.data.IngredientRepository;
import org.example.tacoonline.data.TacoRepository;
import org.example.tacoonline.model.Ingredient;
import org.example.tacoonline.model.Order;
import org.example.tacoonline.model.Taco;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.example.tacoonline.model.Ingredient.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/design")
@RequiredArgsConstructor
@SessionAttributes("order")
public class DesignTacoController {

    final IngredientRepository ingredientRepository;

    final TacoRepository tacoRepository;

    @GetMapping
    public String showDesignForm(Model model) {
//        int x = 0;
//        int y = 1/x;

        fillModelWithIngredients(model);

        //model.addAttribute("tktn", new Taco());
        return "design";
    }

    @ModelAttribute(name = "tktn")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public Order order() {
        return new Order();
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(ing -> ing.getType().equals(type))
                .collect(Collectors.toList());
    }


    @PostMapping
    public String processDesign(@Valid @ModelAttribute(name = "tktn") Taco design, Errors errors, Model model, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            fillModelWithIngredients(model);
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        log.info("Processing Design Taco: {}", saved);
        return "redirect:/orders/current";
    }

    public void fillModelWithIngredients(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        //.forEach(ingredients::add);


//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//
//        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }
}
