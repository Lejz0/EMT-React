package org.example.lab1.repository;

import org.example.lab1.model.Accommodation;
import org.example.lab1.model.enumerations.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Optional<Accommodation> findByName(String name);

    List<Accommodation> findAllByCategory(Category category);
}
