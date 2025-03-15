package com.talhaatif.urlshortener.exception;

public class ResourceMissingException extends RuntimeException {
    public ResourceMissingException(String message) {
        super(message);
    }
}

