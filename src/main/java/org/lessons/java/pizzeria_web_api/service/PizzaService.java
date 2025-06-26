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
public class PizzaService {
    @Autowired
    private PizzasRepository pizzaRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
        }
    public List<Pizza> findAllSortedByTitle() {
        return pizzaRepository.findAll(Sort.by("title"));
    }

    public List<Pizza> findAllSortedByAuthor() {
        return pizzaRepository.findAll(Sort.by("author"));
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);
        if (pizzaAttempt.isEmpty()) {
            //lancio una notFound con response entity
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza con ID " + id + " non trovata.");
        }
            return pizzaAttempt.get();
        }

        public List<Pizza> findByName(String title) {
            return pizzaRepository.findByNameContainingIgnoreCase(title);
        }

        public Pizza create(Pizza pizza) {
            return pizzaRepository.save(pizza);
        }

        public Pizza update(Pizza pizza) {
            return pizzaRepository.save(pizza);
        }

        public void delete(Pizza pizza) {
            for (Ingredient ingredientToDelete : pizza.getIngredients()) {
                ingredientsRepository.delete(ingredientToDelete);
            }
            pizzaRepository.delete(pizza);
        }

        public void deleteById(Integer id) {
            Pizza pizza = getById(id);
            for (Ingredient ingredientToDelete : pizza.getIngredients()) {
                ingredientsRepository.delete(ingredientToDelete);
            }
            pizzaRepository.delete(pizza);
        }

        public Boolean existsById(Integer id) {
            return pizzaRepository.existsById(id);
        }

        public Boolean exists(Pizza pizza) {
            return existsById(pizza.getId());
        }
}
