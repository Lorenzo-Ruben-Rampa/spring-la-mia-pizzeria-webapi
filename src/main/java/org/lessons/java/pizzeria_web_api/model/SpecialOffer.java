package org.lessons.java.pizzeria_web_api.model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="special-offers")
public class SpecialOffer {

    @ManyToOne
    @JoinColumn(name="pizza_id", nullable=false)
    private Pizza pizza;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Il titolo non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il titolo può contenere solo lettere e spazi.")
    @Size(max = 100, message = "Il titolo non può superare i 100 caratteri.")
    private String title;

    @NotNull(message="Il valore dello sconto non può essere vuoto")
    @Min(value = 0, message = "Lo sconto non può essere 0 né < 0")
    private int discount;

    @NotNull
    @FutureOrPresent(message = "La data di inizio non può essere nel passato")
    private LocalDate startDate; 

    @NotNull
    @FutureOrPresent(message = "La data di inizio non può essere nel passato")
    private LocalDate endDate;

    //getters e setters

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pizza getPizza () {
        return this.pizza;

    }

    public void setPizza (Pizza pizza) {
        this.pizza = pizza;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    
    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}

