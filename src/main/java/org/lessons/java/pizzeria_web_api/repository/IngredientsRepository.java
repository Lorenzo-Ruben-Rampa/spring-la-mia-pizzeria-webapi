package org.lessons.java.pizzeria_web_api.repository;
import org.lessons.java.pizzeria_web_api.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
}