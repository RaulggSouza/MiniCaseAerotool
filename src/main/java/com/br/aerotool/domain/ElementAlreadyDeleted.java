package com.br.aerotool.domain;

public class ElementAlreadyDeleted extends RuntimeException {
    public ElementAlreadyDeleted(String message) {
        super(message);
    }
}
