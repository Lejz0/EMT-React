package org.example.lab1.listeners;

import org.example.lab1.model.events.CategoryUnavailableEvent;
import org.example.lab1.service.AccommodationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventHandler {
    private final AccommodationService accommodationService;

    public CategoryEventHandler(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @EventListener
    public void onFilter(CategoryUnavailableEvent event)
    {
        this.accommodationService.onFilter();
    }
}
