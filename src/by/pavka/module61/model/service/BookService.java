package by.pavka.module61.model.service;

import by.pavka.module61.model.entity.book.Book;

import java.util.List;

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

