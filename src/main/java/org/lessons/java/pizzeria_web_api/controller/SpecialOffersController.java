package org.lessons.java.spring_la_mia_pizzeria_relazioni.controller;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;    
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;         
import org.springframework.validation.BindingResult; 
import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.SpecialOffer; 
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.SpecialOffersRepository; 

@Controller
@RequestMapping("/special-offers")
public class SpecialOffersController {

    @Autowired
	private SpecialOffersRepository repository;       
     

	@PostMapping("/create")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
        return "special-offers/create";
    }
    repository.save(formSpecialOffer);              
        return "redirect:/pizzas";
    }

    // --- METODI PER LA MODIFICA ---
    // GET: Mostra il form di modifica con i dati pre-popolati
    @GetMapping("/edit/{id}") 
    public String edit(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        SpecialOffer specialOffer = repository.findById(id).orElse(null);

        if (specialOffer == null) {
            redirectAttributes.addFlashAttribute("error", "Offerta speciale non trovata per la modifica.");
            return "redirect:/pizzas";
        }

        model.addAttribute("specialOffer", specialOffer);
        return "special-offers/create";
    }

    // POST: Salva le modifiche dal form
    @PostMapping("/edit/{id}") 
    public String update(
            @PathVariable("id") Integer id, 
            @Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        SpecialOffer existingOffer = repository.findById(id).orElse(null);

        if (existingOffer == null) {
            redirectAttributes.addFlashAttribute("error", "Offerta speciale non trovata per l'aggiornamento.");
            return "redirect:/pizzas";
        }

        if (bindingResult.hasErrors()) {
            return "special-offers/create";
        }

        existingOffer.setTitle(formSpecialOffer.getTitle());
        existingOffer.setDiscount(formSpecialOffer.getDiscount());
        existingOffer.setStartDate(formSpecialOffer.getStartDate());
        existingOffer.setEndDate(formSpecialOffer.getEndDate());

        repository.save(existingOffer);

        redirectAttributes.addFlashAttribute("message", "Offerta speciale modificata con successo!");
        return "redirect:/pizzas/" + existingOffer.getPizza().getId();
    }

    // POST: Elimina un'offerta speciale
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        SpecialOffer offerToDelete = repository.findById(id).orElse(null);
        if (offerToDelete != null) {
            Integer pizzaId = offerToDelete.getPizza().getId();
            repository.deleteById(id); // Usiamo 'id'
            redirectAttributes.addFlashAttribute("message", "Offerta speciale eliminata con successo!");
            return "redirect:/pizzas/" + pizzaId;
        } else {
            redirectAttributes.addFlashAttribute("error", "Offerta speciale non trovata per l'eliminazione.");
            return "redirect:/pizzas";
        }
    }
}
