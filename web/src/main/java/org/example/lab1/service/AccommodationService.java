package org.example.lab1.service;
import org.example.lab1.model.Accommodation;
import org.example.lab1.model.dto.AccommodationDto;
import org.example.lab1.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> findByName(String name);

    Optional<Accommodation> save(String name, Category category, Long hostId, Integer numRooms);

    Optional<Accommodation> save(AccommodationDto accommodationDto);

    Optional<Accommodation> edit(Long id, String name, Category category, Long hostId, Integer numRooms);

    Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto);

    void deleteById(Long id);

    void markUnavailable(Long id);

    public List<Accommodation> filter(Category category);

    void onFilter();
}
