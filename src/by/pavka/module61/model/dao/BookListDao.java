package by.pavka.module61.model.dao;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.entity.book.Book;
import java.util.List;

public interface BookListDao {
  void addBook(Book book) throws LibraryModelException;

  boolean includeBook(Book book) throws LibraryModelException;

  void removeBook(Book book) throws LibraryModelException;

  boolean excludeBook(Book book) throws LibraryModelException;

  boolean containsBook(Book book) throws LibraryModelException;

  List<Book> listAllBooks() throws LibraryModelException;

  List<Book> sortBooksByTitle() throws LibraryModelException;

  List<Book> sortBooksByAuthors() throws LibraryModelException;

  List<Book> sortBooksByPublisher() throws LibraryModelException;

  List<Book> sortBooksByYear() throws LibraryModelException;

  List<Book> sortBooksByNumberOfPages() throws LibraryModelException;

  List<Book> findBooksByTitle(String title) throws LibraryModelException;

  List<Book> findBooksByAuthors(String[] authors) throws LibraryModelException;

  List<Book> findBooksByPublisher(String publisher) throws LibraryModelException;

  List<Book> findBooksByYear(int year) throws LibraryModelException;

  List<Book> findBooksByNumberOfPages(int pages) throws LibraryModelException;

  // Not implemented by default to be compatible with the previous implementation
  void close();
}
