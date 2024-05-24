package org.example.lab1.service.impl;

import org.example.lab1.model.Accommodation;
import org.example.lab1.model.Host;
import org.example.lab1.model.dto.AccommodationDto;
import org.example.lab1.model.enumerations.Category;
import org.example.lab1.model.events.CategoryUnavailableEvent;
import org.example.lab1.model.exceptions.InvalidAccommodationIdException;
import org.example.lab1.model.exceptions.InvalidHostIdException;
import org.example.lab1.repository.AccommodationRepository;
import org.example.lab1.repository.HostRepository;
import org.example.lab1.service.AccommodationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(ApplicationEventPublisher applicationEventPublisher, AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> findByName(String name) {
        return this.accommodationRepository.findByName(name);
    }

    @Override
    public Optional<Accommodation> save(String name, Category category, Long hostId, Integer numRooms) {
        Host host = this.hostRepository.findById(hostId).orElseThrow(() -> new InvalidHostIdException(hostId));
        Accommodation accommodation = new Accommodation(name, category, host, numRooms);
        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodationDto) {
        Host host = this.hostRepository.findById(accommodationDto.getHostId()).orElseThrow(() -> new InvalidHostIdException(accommodationDto.getHostId()));

        Accommodation accommodation = new Accommodation(accommodationDto.getName(), accommodationDto.getCategory(), host, accommodationDto.getNumRooms());
        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);

    }

    @Override
    public Optional<Accommodation> edit(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new InvalidAccommodationIdException(id));
        Host host = this.hostRepository.findById(hostId).orElseThrow(() -> new InvalidHostIdException(hostId));

        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setHost(host);
        accommodation.setNumRooms(numRooms);

        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);
    }
    @Override
    public Optional<Accommodation> edit(Long id, AccommodationDto accommodationDto) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new InvalidAccommodationIdException(id));
        Host host = this.hostRepository.findById(accommodationDto.getHostId()).orElseThrow(() -> new InvalidHostIdException(accommodationDto.getHostId()));

        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());
        accommodation.setAvailable(accommodationDto.isAvailable());

        this.accommodationRepository.save(accommodation);
        return Optional.of(accommodation);

    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public void markUnavailable(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow(() -> new InvalidAccommodationIdException(id));
        if (accommodation.isAvailable())
        {
            accommodation.setNumRooms(accommodation.getNumRooms() - 1);
            if (accommodation.getNumRooms() == 0)
            {
                accommodation.setAvailable(false);
            }
            this.accommodationRepository.save(accommodation);
        }

    }

    @Override
    public List<Accommodation> filter(Category category) {
        if (category != null)
        {
            List<Accommodation> list = this.accommodationRepository.findAllByCategory(category);
            if (list.isEmpty())
            {
                applicationEventPublisher.publishEvent(new CategoryUnavailableEvent(category));
                return list;
            }
            else
            {
                return this.accommodationRepository.findAllByCategory(category);
            }
        }
        return this.accommodationRepository.findAll();
    }

    @Override
    public void onFilter() {
        System.out.println("ACCOMMODATIONS WITH SELECTED CATEGORY UNAVAILABLE");
    }


}
