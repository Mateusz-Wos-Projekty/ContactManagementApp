package cma.api.exceptions;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
