package by.pavka.module61.controller.command;

import by.pavka.module61.controller.LibraryControllerException;
import by.pavka.module61.controller.request.LibraryRequest;
import by.pavka.module61.model.entity.book.Book;

import java.util.List;

public interface LibraryCommand {
  String FIELD_DELIMITER = ":\\s?";
  String AUTHOR_DELIMITER = ",\\s?";

  List<Book> execute(LibraryRequest request) throws LibraryControllerException;
}
