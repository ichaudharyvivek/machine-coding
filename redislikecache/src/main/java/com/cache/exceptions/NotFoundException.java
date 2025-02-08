package com.cache.exceptions;

/**
 * Exception thrown when data cannot be properly parsed/invalid.
 */
public class NotFoundException extends Exception {

    /**
     * Constructs an InvalidDataException with the specified error message.
     * 
     * @param message The detail message explaining the cause of the exception
     */
    public NotFoundException(String message) {
        super(message);
    }

}