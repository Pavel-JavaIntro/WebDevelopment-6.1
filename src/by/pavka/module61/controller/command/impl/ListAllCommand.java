package by.pavka.module61.controller.command.impl;

import by.pavka.module61.controller.LibraryControllerException;
import by.pavka.module61.controller.command.LibraryCommand;
import by.pavka.module61.controller.request.LibraryRequest;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookService;
import by.pavka.module61.model.service.BookServiceException;
import by.pavka.module61.model.service.impl.BookServiceImpl;

import java.util.List;

public class ListAllCommand implements LibraryCommand {
  @Override
  public List<Book> execute(LibraryRequest request) throws LibraryControllerException {
    BookService bookService = new BookServiceImpl();
    try {
      return bookService.listAllBooks();
    } catch (BookServiceException e) {
      throw new LibraryControllerException("Caught service exception", e);
    }
  }
}
