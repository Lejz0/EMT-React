package org.example.lab1.model.dto;

import lombok.Data;
import org.example.lab1.model.enumerations.Category;

@Data
public class AccommodationDto {

    private String name;

    private Category category;

    private Long hostId;

    private Integer numRooms;

    private boolean isAvailable;
}
