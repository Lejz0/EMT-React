package org.example.lab1.web;

import org.example.lab1.model.Country;
import org.example.lab1.model.dto.CountryDto;
import org.example.lab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/country")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }


    @GetMapping
    public List<Country> getAll()
    {
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable Long id)
    {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet( () -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto countryDto)
    {
        return this.countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id, @RequestBody CountryDto countryDto)
    {
        return this.countryService.edit(id, countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/delete/{id}")
    public  ResponseEntity<Country> deleteById(@PathVariable Long id)
    {
        this.countryService.deleteById(id);
        if (this.countryService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
