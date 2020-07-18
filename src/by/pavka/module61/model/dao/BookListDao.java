package by.pavka.module61.model.dao;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.entity.book.Book;

import java.util.List;

public interface BookListDao {
  void addBook(Book book) throws LibraryModelException;

  boolean includeBook(Book book);

  void removeBook(Book book) throws LibraryModelException;

  boolean excludeBook(Book book);

  boolean containsBook(Book book) throws LibraryModelException;

  List<Book> listAllBooks();

  List<Book> sortBooksByTitle();

  List<Book> sortBooksByAuthors();

  List<Book> sortBooksByPublisher();

  List<Book> sortBooksByYear();

  List<Book> sortBooksByNumberOfPages();

  List<Book> findBooksByTitle(String title);

  List<Book> findBooksByAuthors(String[] authors);

  List<Book> findBooksByPublisher(String publisher);

  List<Book> findBooksByYear(int year);

  List<Book> findBooksByNumberOfPages(int pages);

  void close();
}
