package org.example.lab1.model.exceptions;

public class InvalidAccommodationIdException extends RuntimeException{
    public InvalidAccommodationIdException(Long id) {
        super(String.format("Accommodation with %d was not found!", id));
    }
}
