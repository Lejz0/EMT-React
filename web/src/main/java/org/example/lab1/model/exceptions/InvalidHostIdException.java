package org.example.lab1.model.exceptions;

public class InvalidHostIdException extends RuntimeException{
    public InvalidHostIdException(Long id) {
        super(String.format("Host with %d was not found!", id));
    }
}
