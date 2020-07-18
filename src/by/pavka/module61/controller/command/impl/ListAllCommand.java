package by.pavka.module61.controller.command.impl;

import by.pavka.module61.controller.command.LibraryCommand;
import by.pavka.module61.controller.request.LibraryRequest;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookService;
import by.pavka.module61.model.service.impl.BookServiceImpl;

import java.util.List;

public class ListAllCommand implements LibraryCommand {
  @Override
  public List<Book> execute(LibraryRequest request) {
    BookService bookService = new BookServiceImpl();
    return bookService.listAllBooks();
  }
}
