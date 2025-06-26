package org.lessons.java.pizzeria_web_api.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.lessons.java.pizzeria_web_api.service.PizzaService;
import org.lessons.java.pizzeria_web_api.model.Pizza;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@CrossOrigin
@RequestMapping("api/pizzas")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index() {
        List<Pizza> pizzas = pizzaService.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public Pizza show(@PathVariable Integer id) {
        Pizza pizza = pizzaService.getById(id);
        return pizza;
    }
    
    @PostMapping
    public Pizza store(@RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }
    
    @PutMapping("/{id}")
    public Pizza update(@RequestBody Pizza pizza, @PathVariable Integer id) {
        pizza.setId(id); 
        return pizzaService.update(pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pizzaService.deleteById(id);
    }

}
