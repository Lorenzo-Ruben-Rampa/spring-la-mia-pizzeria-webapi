package org.lessons.java.pizzeria_web_api.repository;
import org.lessons.java.pizzeria_web_api.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PizzasRepository extends JpaRepository <Pizza, Integer> {
    // Nuovo metodo per cercare pizze il cui nome contiene una certa stringa (case-insensitive)
    List<Pizza> findByNameContainingIgnoreCase(String name);
}
