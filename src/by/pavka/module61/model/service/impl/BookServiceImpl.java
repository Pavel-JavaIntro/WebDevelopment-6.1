package by.pavka.module61.model.service.impl;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.impl.SqlBookListDao;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookService;
import by.pavka.module61.model.service.BookServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
  @Override
  public List<Book> addBook(String[] bookData) throws BookServiceException {
    Book book = constructBook(bookData);
    BookListDao bookListDao;
    try {
      bookListDao = new SqlBookListDao();
      bookListDao.addBook(book);
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while adding book", e);
    }
    List<Book> books = new ArrayList<>();
    books.add(book);
    bookListDao.close();
    return books;
  }

  @Override
  public List<Book> includeBook(String[] bookData) throws BookServiceException {
    Book book = constructBook(bookData);
    BookListDao bookListDao;
    List<Book> books = new ArrayList<>();
    try {
      bookListDao = new SqlBookListDao();
      if (bookListDao.includeBook(book)) {
        books.add(book);
      }
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while including book", e);
    }
    bookListDao.close();
    return books;
  }

  @Override
  public List<Book> removeBook(String[] bookData) throws BookServiceException {
    Book book = constructBook(bookData);
    BookListDao bookListDao;
    try {
      bookListDao = new SqlBookListDao();
      bookListDao.removeBook(book);
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while removing book", e);
    }
    List<Book> books = new ArrayList<>();
    books.add(book);
    bookListDao.close();
    return books;
  }

  @Override
  public List<Book> excludeBook(String[] bookData) throws BookServiceException {
    Book book = constructBook(bookData);
    BookListDao bookListDao;
    List<Book> books = new ArrayList<>();
    try {
      bookListDao = new SqlBookListDao();
      if (bookListDao.excludeBook(book)) {
        books.add(book);
      }
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while excluding book", e);
    }
    bookListDao.close();
    return books;
  }

  private Book constructBook(String[] bookData) throws BookServiceException {
    String title = bookData[0];
    String[] authors = bookData[1].split(",\\s?");
    String publisher = bookData[2];
    String yearString = bookData[3];
    String pageString = bookData[4];
    int yearOfPublication = verifyYear(yearString);
    int numberOfPages = verifyPages(pageString);
    Book book;
    try {
      book = new Book(title, authors, publisher, yearOfPublication, numberOfPages);
    } catch (LibraryModelException e) {
      throw new BookServiceException("Book without title", e);
    }
    return book;
  }

  private int verifyYear(String yearString) throws BookServiceException {
    int yearOfPublication;
    try {
      yearOfPublication = Integer.parseInt(yearString.trim());
    } catch (NumberFormatException e) {
      throw new BookServiceException("Wrong yearOfPublication format");
    }
    if (yearOfPublication > LocalDate.now().getYear()) {
      throw new BookServiceException("Wrong yearOfPublication");
    }
    return yearOfPublication;
  }

  private int verifyPages(String pageString) throws BookServiceException {
    int numberOfPages;
    try {
      numberOfPages = Integer.parseInt(pageString.trim());
    } catch (NumberFormatException e) {
      throw new BookServiceException("Wrong numberOPages format");
    }
    if (numberOfPages < 1) {
      throw new BookServiceException("Wrong numberOPages");
    }
    return numberOfPages;
  }

  @Override
  public List<Book> listAllBooks() throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.listAllBooks();
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while listing book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> sortByTitle() throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.sortBooksByTitle();
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while sorting book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> sortByAuthors() throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.sortBooksByAuthors();
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while sorting book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> sortByPublisher() throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.sortBooksByPublisher();
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while sorting book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> sortByYear() throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.sortBooksByYear();
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while sorting book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> sortByNumberOfPages() throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.sortBooksByNumberOfPages();
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while sorting book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> findByTitle(String searchValue) throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.findBooksByTitle(searchValue);
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while finding book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> findByAuthors(String[] searchValue) throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.findBooksByAuthors(searchValue);
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while finding book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> findByPublisher(String searchValue) throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.findBooksByPublisher(searchValue);
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while finding book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> findByYear(String searchValue) throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.findBooksByYear(verifyYear(searchValue));
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while finding book", e);
    }
    bookListDao.close();
    return result;
  }

  @Override
  public List<Book> findByNumberOfPages(String searchValue) throws BookServiceException {
    BookListDao bookListDao;
    List<Book> result;
    try {
      bookListDao = new SqlBookListDao();
      result = bookListDao.findBooksByNumberOfPages(verifyPages(searchValue));
    } catch (LibraryModelException e) {
      throw new BookServiceException("Caught CRUD exception while finding book", e);
    }
    bookListDao.close();
    return result;
  }
}

