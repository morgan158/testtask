package com.task.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id) {
        super("Could not fount dict word with id " + id);
    }
}
