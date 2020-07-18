package by.pavka.module61.model.dao.impl;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookServiceException;
import by.pavka.module61.model.service.WrapperConnector;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class SQLBookListDao implements BookListDao {
  private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS books" +
      "(id INTEGER AUTO_INCREMENT," +
      "title VARCHAR(150)," +
      "authors VARCHAR(150)," +
      "publisher VARCHAR(100)," +
      "year INTEGER," +
      "pages INTEGER," +
      "PRIMARY KEY (id))";
  private static final String ADD_BOOK = "INSERT INTO books (title, authors, publisher, year, pages) VALUES (?, ?, ?, ?, ?)";
  private static final String CONTAINS_BOOK = "SELECT * FROM books WHERE title=? AND authors=? " +
      "AND publisher=? AND year=? AND pages=?";

  private WrapperConnector connector;

  public SQLBookListDao() throws LibraryModelException {
    try {
      connector = new WrapperConnector();
      Statement statement = connector.obtainStatement();
      statement.execute(CREATE_TABLE);
      connector.closeStatement(statement);
    } catch (BookServiceException e) {
      throw new LibraryModelException("DAO cannot obtain connection");
    } catch (SQLException e) {
      throw new LibraryModelException("Library cannot be created");
    }
  }

  @Override
  public void addBook(Book book) throws LibraryModelException {
    if (!containsBook(book)) {
      try {
        PreparedStatement statement = connector.obtainPreparedStatement(ADD_BOOK);
        statement.setString(1, book.getTitle());
        statement.setString(2, Arrays.toString(book.getAuthors()));
        statement.setString(3, book.getPublisher());
        statement.setInt(4, book.getYearOfPublication());
        statement.setInt(5, book.getNumberOfPages());
        statement.executeUpdate();
        connector.closeStatement(statement);
      } catch (BookServiceException | SQLException e) {
        throw new LibraryModelException("Book not added");
      }
    }
  }

  @Override
  public boolean includeBook(Book book) {
    return false;
  }

  @Override
  public void removeBook(Book book) throws LibraryModelException {

  }

  @Override
  public boolean excludeBook(Book book) {
    return false;
  }

  @Override
  public boolean containsBook(Book book) throws LibraryModelException {
    boolean result = false;
    try {
      PreparedStatement statement = connector.obtainPreparedStatement(CONTAINS_BOOK);
      statement.setString(1, book.getTitle());
      statement.setString(2, Arrays.toString(book.getAuthors()));
      statement.setString(3, book.getPublisher());
      statement.setInt(4, book.getYearOfPublication());
      statement.setInt(5, book.getNumberOfPages());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        result = true;
      }
      connector.closeStatement(statement);
    } catch (BookServiceException | SQLException e) {
      throw new LibraryModelException("Caught SQL exception", e);
    }
    return result;
  }

  @Override
  public List<Book> listAllBooks() {
    return null;
  }

  @Override
  public List<Book> sortBooksByTitle() {
    return null;
  }

  @Override
  public List<Book> sortBooksByAuthors() {
    return null;
  }

  @Override
  public List<Book> sortBooksByPublisher() {
    return null;
  }

  @Override
  public List<Book> sortBooksByYear() {
    return null;
  }

  @Override
  public List<Book> sortBooksByNumberOfPages() {
    return null;
  }

  @Override
  public List<Book> findBooksByTitle(String title) {
    return null;
  }

  @Override
  public List<Book> findBooksByAuthors(String[] authors) {
    return null;
  }

  @Override
  public List<Book> findBooksByPublisher(String publisher) {
    return null;
  }

  @Override
  public List<Book> findBooksByYear(int year) {
    return null;
  }

  @Override
  public List<Book> findBooksByNumberOfPages(int pages) {
    return null;
  }

  @Override
  public void close() {

  }

}
