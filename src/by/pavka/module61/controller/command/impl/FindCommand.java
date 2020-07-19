package by.pavka.module61.controller.command.impl;

import by.pavka.module61.controller.LibraryControllerException;
import by.pavka.module61.controller.command.LibraryCommand;
import by.pavka.module61.controller.request.LibraryRequest;
import by.pavka.module61.controller.type.BookTagType;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookService;
import by.pavka.module61.model.service.BookServiceException;
import by.pavka.module61.model.service.impl.BookServiceImpl;

import java.util.List;

public class FindCommand implements LibraryCommand {
  @Override
  public List<Book> execute(LibraryRequest request) throws LibraryControllerException {
    BookTagType tag = request.getTagType();
    String searchValue = request.getData();
    BookService bookService = new BookServiceImpl();
    try {
      switch (tag) {
        case TITLE:
          return bookService.findByTitle(searchValue);
        case AUTHORS:
          return bookService.findByAuthors(searchValue.split(AUTHOR_DELIMITER));
        case PUBLISHER:
          return bookService.findByPublisher(searchValue);
        case YEAR:
          return bookService.findByYear(searchValue);
        case PAGES:
          return bookService.findByNumberOfPages(searchValue);
        default:
          throw new LibraryControllerException("Tag not supported yet");
      }
    } catch (BookServiceException e) {
      throw new LibraryControllerException("Caught service exception", e);
    }
  }
}

