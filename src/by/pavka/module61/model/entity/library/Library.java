package by.pavka.module61.model.entity.library;

import by.pavka.module61.model.entity.book.Book;

import java.util.List;

public interface Library {
  boolean insert(Book book);

  boolean delete(Book book);

  boolean contains(Book book);

  List<Book> listAll();
}

