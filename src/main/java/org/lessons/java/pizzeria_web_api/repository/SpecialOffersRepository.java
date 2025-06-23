package org.lessons.java.pizzeria_web_api.repository;
import org.lessons.java.pizzeria_web_api.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOffersRepository extends JpaRepository<SpecialOffer, Integer> {
	
}

