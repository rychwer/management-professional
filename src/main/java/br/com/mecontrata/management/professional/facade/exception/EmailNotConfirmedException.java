package br.com.mecontrata.management.professional.facade.exception;

public class EmailNotConfirmedException extends RuntimeException {

    public EmailNotConfirmedException(String message) {
        super(message);
    }

}
