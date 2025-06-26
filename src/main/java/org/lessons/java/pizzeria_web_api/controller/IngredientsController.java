package org.lessons.java.pizzeria_web_api.controller;
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
import org.lessons.java.pizzeria_web_api.model.Pizza;
import org.lessons.java.pizzeria_web_api.model.Ingredient;
import org.lessons.java.pizzeria_web_api.repository.IngredientsRepository;
import org.lessons.java.pizzeria_web_api.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

    @Autowired
    private IngredientService ingredientService;

    //INDEX
    @GetMapping
    public String index (Model model) {
        List<Ingredient> ingredients;
        ingredients = ingredientService.findAll();
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
        ingredientService.create(ingredient);
        return "redirect:/ingredients";
    }

    //UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);                   

        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }
        ingredientService.update(ingredient);
        return  "redirect:/ingredients";
    }

    //SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model, @RequestParam(name="IngredientName", required=false) String searchIngredientName, RedirectAttributes redirectAttributes) {
        Ingredient ingredient = ingredientService.getById(id);
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
    Ingredient ingredientToDelete = ingredientService.getById(id);
    for (Pizza linkedPizza : ingredientToDelete.getPizzas()) {
        linkedPizza.getIngredients().remove(ingredientToDelete);
    }
    ingredientService.delete(ingredientToDelete);
        return "redirect:/ingredients";
    }
}
