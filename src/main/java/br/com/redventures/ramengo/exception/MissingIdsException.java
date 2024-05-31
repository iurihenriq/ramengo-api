package br.com.redventures.ramengo.exception;

public class MissingIdsException extends RuntimeException {
    public MissingIdsException() {
        super("both brothId and proteinId are required");
    }
}