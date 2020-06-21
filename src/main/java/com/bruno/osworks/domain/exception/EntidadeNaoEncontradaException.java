package com.bruno.osworks.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
    private final static long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
