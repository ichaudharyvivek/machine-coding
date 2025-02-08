package com.cache.exceptions;

/**
 * Exception thrown when attempting to parse or store invalid data in the cache.
 * This occurs when a value cannot be converted to the expected data type or
 * when the input format is not supported by the cache operations.
 */
public class InvalidDataException extends Exception {

    /**
     * Constructs an InvalidDataException with a message detailing the parsing or
     * validation error.
     * 
     * @param message Detailed explanation of why the data is considered invalid
     */
    public InvalidDataException(String message) {
        super(message);
    }

}