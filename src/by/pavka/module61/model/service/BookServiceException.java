package by.pavka.module61.model.service;

public class BookServiceException extends Exception {
    public BookServiceException() {}

    public BookServiceException(String message) {
        super(message);
    }

    public BookServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookServiceException(Throwable cause) {
        super(cause);
    }
}

