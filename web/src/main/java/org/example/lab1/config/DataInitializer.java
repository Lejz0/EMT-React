package org.example.lab1.config;

import jakarta.annotation.PostConstruct;
import org.example.lab1.model.enumerations.Category;
import org.example.lab1.service.AccommodationService;
import org.example.lab1.service.CountryService;
import org.example.lab1.service.HostService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final CountryService countryService;

    public DataInitializer(AccommodationService accommodationService, HostService hostService, CountryService countryService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void init(){
        for (int i = 1; i < 3; i++) {
            this.countryService.save("Country" + i, "Continent" + i);
        }

        for (int i = 1; i < 3; i++) {
            this.hostService.save("HostName" + i, "HostSurname" + i, Long.valueOf(i));
        }

        for (int i = 1; i < 3; i++) {
            this.accommodationService.save("AccName" + i, Category.HOTEL, Long.valueOf(i), i);
        }
    }
}
