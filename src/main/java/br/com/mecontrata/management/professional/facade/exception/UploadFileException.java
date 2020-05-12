package br.com.mecontrata.management.professional.facade.exception;

public class UploadFileException extends RuntimeException {

    public UploadFileException(String message, Throwable ex) {
        super(message, ex);
    }

}
