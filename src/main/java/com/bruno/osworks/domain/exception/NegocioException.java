package com.bruno.osworks.domain.exception;

public class NegocioException extends RuntimeException {
    private final static long serialVersionUID = 1L;

    public NegocioException(String message) {
        super(message);
    }
}
