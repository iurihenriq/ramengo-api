package br.com.redventures.ramengo.exception;

public class OrderPlacementException extends RuntimeException {
    public OrderPlacementException() {
        super("could not place order");
    }
}