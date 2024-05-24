package org.example.lab1.web;

import org.example.lab1.model.Accommodation;
import org.example.lab1.model.dto.AccommodationDto;
import org.example.lab1.model.enumerations.Category;
import org.example.lab1.model.exceptions.InvalidAccommodationIdException;
import org.example.lab1.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/acc")
public class AccommodationRestController {
    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }


    @GetMapping
    public List<Accommodation> getAll(@RequestParam(required = false) Category category)
    {
        if (category != null)
        {
            return this.accommodationService.filter(category);
        }
        return this.accommodationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getById(@PathVariable Long id)
    {
        return this.accommodationService.findById(id)
                .map( acc -> ResponseEntity.ok().body(acc))
                .orElseGet( () -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> addAcc(@RequestBody AccommodationDto accommodationDto)
    {
        return this.accommodationService.save(accommodationDto)
                .map(acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> editAcc(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto)
    {
        return this.accommodationService.edit(id, accommodationDto)
                .map( acc -> ResponseEntity.ok().body(acc))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Accommodation> deleteById(@PathVariable Long id)
    {
        this.accommodationService.deleteById(id);
        if (this.accommodationService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/mark/{id}")
    public ResponseEntity<Accommodation> markUnavailable(@PathVariable Long id)
    {

        Accommodation accommodation = this.accommodationService.findById(id).orElseThrow(() -> new InvalidAccommodationIdException(id));
        this.accommodationService.markUnavailable(id);
        return ResponseEntity.ok().build();

    }
    //    @PostMapping("/mark/{id}")
//    public ResponseEntity<Accommodation> markUnavailable(@PathVariable Long id)
//    {
//
//        Accommodation accommodation = this.accommodationService.findById(id).orElseThrow(() -> new InvalidAccommodationIdException(id));
//        if (accommodation.isAvailable())
//        {
//            this.accommodationService.markUnavailable(id);
//            return ResponseEntity.ok().build();
//        }
//        else {
//            return ResponseEntity.badRequest().build();
//        }
//
//    }



}
