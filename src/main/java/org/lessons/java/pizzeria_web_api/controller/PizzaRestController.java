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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;


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
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        if (pizza.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaService.create(pizza), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza, @PathVariable Integer id) {
      

         if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
          pizza.setId(id); 
        return new ResponseEntity<Pizza>(pizzaService.update(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        pizzaService.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatus.OK);
    }

}
