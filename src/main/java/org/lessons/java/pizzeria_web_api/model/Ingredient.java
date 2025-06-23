package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas;

    @NotEmpty(message = "Il nome dell'ingrediente non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il nome può contenere solo lettere e spazi.")
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri.")
    private String name;

    @NotBlank(message="Questo campo è obbligatorio")
    @Column(name = "url_image")
    private String urlImage;

    //Getter and Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

        public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
