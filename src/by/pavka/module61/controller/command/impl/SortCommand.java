package by.pavka.module61.controller.command.impl;

import by.pavka.module61.controller.LibraryControllerException;
import by.pavka.module61.controller.command.LibraryCommand;
import by.pavka.module61.controller.request.LibraryRequest;
import by.pavka.module61.controller.type.BookTagType;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookService;
import by.pavka.module61.model.service.impl.BookServiceImpl;

import java.util.List;

public class SortCommand implements LibraryCommand {
  @Override
  public List<Book> execute(LibraryRequest request) throws LibraryControllerException {
    BookTagType tag = request.getTagType();
    BookService bookService = new BookServiceImpl();
    switch (tag) {
      case TITLE:
        return bookService.sortByTitle();
      case AUTHORS:
        return bookService.sortByAuthors();
      case PUBLISHER:
        return bookService.sortByPublisher();
      case YEAR:
        return bookService.sortByYear();
      case PAGES:
        return bookService.sortByNumberOfPages();
      default:
        throw new LibraryControllerException("Tag not supported yet");
    }
  }
}

