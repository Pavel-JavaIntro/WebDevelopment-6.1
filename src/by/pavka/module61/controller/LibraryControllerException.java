package by.pavka.module61.controller;

public class LibraryControllerException extends Exception {
  public LibraryControllerException() {
  }

  public LibraryControllerException(String message) {
    super(message);
  }

  public LibraryControllerException(String message, Throwable cause) {
    super(message, cause);
  }

  public LibraryControllerException(Throwable cause) {
    super(cause);
  }
}
