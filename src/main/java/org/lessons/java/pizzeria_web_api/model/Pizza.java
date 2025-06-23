package org.lessons.java.pizzeria_web_api.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import java.util.List;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="pizzas")
public class Pizza {

    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)   
    private List<SpecialOffer> specialOffers;

    @ManyToMany()
    @JoinTable(
        name = "pizza_ingredient",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn (name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Il nome non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il nome può contenere solo lettere e spazi.")
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri.")
    private String name;

    @Lob
    @Column(nullable=false)
    @NotBlank(message = "La descrizione non può essere vuota.")
    @Size(max = 455, message = "La descrizione non può superare i 455 caratteri.")
    private String description;

    @NotBlank(message="Questo campo è obbligatorio")
    @Column(name = "url_image")
    private String urlImage;

    @NotNull(message="Il prezzo non può essere vuoto o < di zero")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal price;

    //Getter e Setter
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

// getters e setters di Special Offers

    public List<SpecialOffer> getSpecialOffers() {
	return this.specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

// getters e setters di Ingredients
    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    // NUOVO METODO: Verifica se la pizza HA QUALSIASI OFFERTA SPECIALE (attiva o meno)
    public boolean isSpecialOffer () {
        return this.specialOffers != null && !this.specialOffers.isEmpty();
    }

    public SpecialOffer getActiveSpecialOffer() {
        LocalDate today = LocalDate.now();
        if (isSpecialOffer()) { 
            for (SpecialOffer offer : this.specialOffers) {
                if (!today.isBefore(offer.getStartDate()) && !today.isAfter(offer.getEndDate())) {
                    return offer;
                }
            }
        }
        return null;
    }

    // METODO PER GLI SCONTI (senza Optional)
    public BigDecimal getEffectivePrice() {
        SpecialOffer activeOffer = getActiveSpecialOffer(); // Ottiene l'offerta attiva (potrebbe essere null)

    // Controlla se activeOffer NON è null E se lo sconto è valido
    if (activeOffer != null) {
    Integer discount = activeOffer.getDiscount();
    if (discount != null && discount > 0) { // Qui 'discount' è Integer, ma 'discount > 0' funziona per auto-unboxing sicuro
        BigDecimal discountPercentage = new BigDecimal(discount).divide(new BigDecimal(100), 2, java.math.RoundingMode.HALF_UP);
        BigDecimal discountedPrice = this.price.multiply(BigDecimal.ONE.subtract(discountPercentage));
        return discountedPrice.setScale(2, java.math.RoundingMode.HALF_UP);
    }
    }
        return this.price;
        }

	@Override
	public String toString(){
	  return  String.format(this.name, this.description, this.price);
    }

}