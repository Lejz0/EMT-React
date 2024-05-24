package org.example.lab1.model.events;

import lombok.Getter;
import org.example.lab1.model.enumerations.Category;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class CategoryUnavailableEvent extends ApplicationEvent {

    private LocalDateTime when;
    public CategoryUnavailableEvent(Category category) {
        super(category);
    }
}
