package br.com.redventures.ramengo.exception;

public class ApiKeyMissingException extends RuntimeException {
    public ApiKeyMissingException() {
        super("x-api-key header missing");
    }
}
