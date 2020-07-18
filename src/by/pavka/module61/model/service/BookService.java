package by.pavka.module61.model.service;

import by.pavka.module61.model.entity.book.Book;

import java.util.List;

public interface BookService {
    List<Book> addBook(String[] bookData) throws BookServiceException;

    List<Book> includeBook(String[] bookData) throws BookServiceException;

    List<Book> removeBook(String[] bookData) throws BookServiceException;

    List<Book> excludeBook(String[] bookData) throws BookServiceException;

    List<Book> listAllBooks();

    List<Book> sortByTitle();

    List<Book> sortByAuthors();

    List<Book> sortByPublisher();

    List<Book> sortByYear();

    List<Book> sortByNumberOfPages();

    List<Book> findByTitle(String searchValue);

    List<Book> findByAuthors(String[] searchValue);

    List<Book> findByPublisher(String searchValue);

    List<Book> findByYear(String searchValue) throws BookServiceException;

    List<Book> findByNumberOfPages(String searchValue) throws BookServiceException;
}

