package org.lessons.java.pizzeria_web_api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.lessons.java.pizzeria_web_api.repository.PizzasRepository;
import org.lessons.java.pizzeria_web_api.model.Ingredient;
import org.lessons.java.pizzeria_web_api.model.Pizza;
import java.util.List;
import java.util.Optional;
import org.lessons.java.pizzeria_web_api.repository.IngredientsRepository;


@Service
public class IngredientService {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public List<Ingredient> findAll() {
        return ingredientsRepository.findAll();
    }

    public Ingredient create (Ingredient ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientAttempt = ingredientsRepository.findById(id);
        if (ingredientAttempt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingredient with ID " + id + " not found.");
        }
        return ingredientAttempt.get();
    }

    public void delete(Ingredient ingredient) {
        ingredientsRepository.delete(ingredient);
    }

    public void deleteById(Integer id) {
        Ingredient ingredient = getById(id);
        delete(ingredient);
    }

}