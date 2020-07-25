package by.pavka.module61.model.service;

import by.pavka.module61.model.entity.book.Book;

import java.util.List;

/*
 * This interface contains "duplicating" methods like "sortBy..." and "findBy..." to be
 * compatible with the previous "Books and Library App" implementation based on ArrayList
 * Library. Also method pairs "addBook"/"includeBook" and "deleteBook"/"excludeBook" are
 * maintained to meet the initial requirements of exception handling
 */
public interface BookService {
  List<Book> addBook(String[] bookData) throws BookServiceException;

  List<Book> includeBook(String[] bookData) throws BookServiceException;

  List<Book> removeBook(String[] bookData) throws BookServiceException;

  List<Book> excludeBook(String[] bookData) throws BookServiceException;

  List<Book> listAllBooks() throws BookServiceException;

  List<Book> sortByTitle() throws BookServiceException;

  List<Book> sortByAuthors() throws BookServiceException;

  List<Book> sortByPublisher() throws BookServiceException;

  List<Book> sortByYear() throws BookServiceException;

  List<Book> sortByNumberOfPages() throws BookServiceException;

  List<Book> findByTitle(String searchValue) throws BookServiceException;

  List<Book> findByAuthors(String[] searchValue) throws BookServiceException;

  List<Book> findByPublisher(String searchValue) throws BookServiceException;

  List<Book> findByYear(String searchValue) throws BookServiceException;

  List<Book> findByNumberOfPages(String searchValue) throws BookServiceException;
}

