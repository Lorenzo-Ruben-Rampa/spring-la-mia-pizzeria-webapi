package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.util.List;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientsRepository ingredientRepository;

    //INDEX
    @GetMapping
    public String index (Model model) {
        List<Ingredient> ingredients;
        ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    // CREATE 
    @GetMapping("/create") 
    public String create(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);
        return "ingredients/create-or-edit";
    }

    // store del CREATE
    @PostMapping("/create")
    public String store (@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/ingredients/create-or-edit";
        }
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);                   

        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }
        ingredientRepository.save(ingredient);
        return  "redirect:/ingredients";
    }

    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model, @RequestParam(name="IngredientName", required=false) String searchIngredientName, RedirectAttributes redirectAttributes) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (ingredient == null) { 
            redirectAttributes.addFlashAttribute("error", "Ingrediente non trovato!");
        return "redirect:/ingredients"; 
        }
        model.addAttribute("ingredient", ingredient); 
        return "ingredients/show"; 
    }

    //DELETE
    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
    Ingredient ingredientToDelete = ingredientRepository.findById(id).get();
    for (Pizza linkedPizza : ingredientToDelete.getPizzas()) {
        linkedPizza.getIngredients().remove(ingredientToDelete);
    }
    ingredientRepository.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }
}
