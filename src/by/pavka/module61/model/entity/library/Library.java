package by.pavka.module61.model.entity.library;

import by.pavka.module61.model.entity.book.Book;

import java.util.List;

// Outdated interface from the original "Books and Library" ArrayList implementation
public interface Library {
  boolean insert(Book book);

  boolean delete(Book book);

  boolean contains(Book book);

  List<Book> listAll();
}

