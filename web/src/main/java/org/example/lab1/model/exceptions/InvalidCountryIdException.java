package org.example.lab1.model.exceptions;

public class InvalidCountryIdException extends RuntimeException{
    public InvalidCountryIdException(Long id) {
        super(String.format("Country with %d was not found!", id));
    }
}
