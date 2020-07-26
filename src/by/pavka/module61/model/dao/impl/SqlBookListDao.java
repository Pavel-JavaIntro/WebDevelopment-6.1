package by.pavka.module61.model.dao.impl;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.DaoException;
import by.pavka.module61.model.entity.book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlBookListDao implements BookListDao {
  private static final String ADD_BOOK = "INSERT INTO books (title, authors, publisher, year, " +
      "pages) VALUES(?, ?, ?, ?, ?)";
  private static final String REMOVE_BOOK = "DELETE FROM books WHERE title=? AND authors=? " +
      "AND publisher=? AND year=? AND pages=?";
  private static final String CONTAINS_BOOK = "SELECT title, authors, publisher, year, pages " +
      "FROM books WHERE title=? AND authors=? " +
      "AND publisher=? AND year=? AND pages=?";
  private static final String LIST_ALL = "SELECT title, authors, publisher, year, pages FROM books";
  private static final String FIND_BOOKS = "SELECT title, authors, publisher, year, pages FROM books WHERE ";
  private static final String SORT_BOOKS = "SELECT title, authors, publisher, year, pages FROM books ORDER BY ";
  private static final String COLUMN_TITLE = "title";
  private static final String COLUMN_AUTHORS = "authors";
  private static final String COLUMN_PUBLISHER = "publisher";
  private static final String COLUMN_YEAR = "year";
  private static final String COLUMN_NUMBER_OF_PAGES = "pages";
  private static final String AUTHOR_DELIMITER = ",\\s?";

  private WrapperConnector connector;

  public SqlBookListDao() throws LibraryModelException {
    try {
      connector = new WrapperConnector();
    } catch (DaoException e) {
      throw new LibraryModelException("DAO cannot obtain connection", e);
    }
  }

  //This method realizes the original requirement to throw an exception if the book exists. For
  // "boolean" method see the method includeBook below
  @Override
  public void addBook(Book book) throws LibraryModelException {
    if (book != null && !containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(ADD_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not added because of SQL or Dao exception", e);
      } finally {
        connector.closeStatement(statement);
      }
    } else {
      throw new LibraryModelException("Book not added");
    }
  }

  @Override
  public boolean includeBook(Book book) throws LibraryModelException {
    if (book != null && !containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(ADD_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
        return true;
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not included because of SQL or Dao exception", e);
      } finally {
        connector.closeStatement(statement);
      }
    }
    return false;
  }

  // This method realizes the original requirement to throw an exception if the book
  // doesn't exists. For "boolean" method see the method exclude Book below
  @Override
  public void removeBook(Book book) throws LibraryModelException {
    if (containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(REMOVE_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not removed because of SQL or Dao exception", e);
      } finally {
        connector.closeStatement(statement);
      }
    } else {
      throw new LibraryModelException("Book not removed");
    }

  }

  @Override
  public boolean excludeBook(Book book) throws LibraryModelException {
    if (containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(REMOVE_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
        return true;
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not removed because of SQL or Dao exception", e);
      } finally {
        connector.closeStatement(statement);
      }
    }
    return false;
  }

  private void setBookIntoStatement(Book book, PreparedStatement statement) throws SQLException {
    statement.setString(1, book.getTitle());
    String authors = Arrays.toString(book.getAuthors());
    statement.setString(2, authors.substring(1, authors.length() - 1));
    statement.setString(3, book.getPublisher());
    statement.setInt(4, book.getYearOfPublication());
    statement.setInt(5, book.getNumberOfPages());
  }

  @Override
  public boolean containsBook(Book book) throws LibraryModelException {
    boolean result = false;
    PreparedStatement statement = null;
    try {
      statement = connector.obtainPreparedStatement(CONTAINS_BOOK);
      setBookIntoStatement(book, statement);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          result = true;
        }
      }
    } catch (DaoException | SQLException e) {
      throw new LibraryModelException("Caught connection or SQL exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return result;
  }

  @Override
  public List<Book> listAllBooks() throws LibraryModelException {
    return formResultList(LIST_ALL);
  }

  private List<Book> formResultList(String sql) throws LibraryModelException {
    List<Book> resultList = new ArrayList<>();
    Statement statement = null;
    try {
      statement = connector.obtainStatement();
      try (ResultSet resultSet = statement.executeQuery(sql)) {
        if (resultSet != null) {
          while (resultSet.next()) {
            String title = resultSet.getString(COLUMN_TITLE);
            String[] authors = resultSet.getString(COLUMN_AUTHORS).split(AUTHOR_DELIMITER);
            String publisher = resultSet.getString(COLUMN_PUBLISHER);
            int year = resultSet.getInt(COLUMN_YEAR);
            int pages = resultSet.getInt(COLUMN_NUMBER_OF_PAGES);
            Book book = new Book(title, authors, publisher, year, pages);
            resultList.add(book);
          }
        }
      }
    } catch (DaoException | SQLException e) {
      throw new LibraryModelException("Caught connection or SQL exception", e);
    } finally {
      connector.closeStatement(statement);
    }
    return resultList;
  }

  @Override
  public List<Book> sortBooksByTitle() throws LibraryModelException {
    return formResultList(SORT_BOOKS + COLUMN_TITLE);
  }

  @Override
  public List<Book> sortBooksByAuthors() throws LibraryModelException {
    return formResultList(SORT_BOOKS + COLUMN_AUTHORS);
  }

  @Override
  public List<Book> sortBooksByPublisher() throws LibraryModelException {
    return formResultList(SORT_BOOKS + COLUMN_PUBLISHER);
  }

  @Override
  public List<Book> sortBooksByYear() throws LibraryModelException {
    return formResultList(SORT_BOOKS + COLUMN_YEAR);
  }

  @Override
  public List<Book> sortBooksByNumberOfPages() throws LibraryModelException {
    return formResultList(SORT_BOOKS + COLUMN_NUMBER_OF_PAGES);
  }

  @Override
  public List<Book> findBooksByTitle(String title) throws LibraryModelException {
    String sql = FIND_BOOKS + COLUMN_TITLE + "='" + title + "'";
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByAuthors(String[] authors) throws LibraryModelException {
    String authorString = Arrays.toString(authors);
    String sql =
        FIND_BOOKS + COLUMN_AUTHORS + "='" + authorString.substring(1, authorString.length() - 1) +
            "'";
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByPublisher(String publisher) throws LibraryModelException {
    String sql = FIND_BOOKS + COLUMN_PUBLISHER + "='" + publisher + "'";
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByYear(int year) throws LibraryModelException {
    String sql = FIND_BOOKS + COLUMN_YEAR + "=" + year;
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByNumberOfPages(int pages) throws LibraryModelException {
    String sql = FIND_BOOKS + COLUMN_NUMBER_OF_PAGES + "=" + pages;
    return formResultList(sql);
  }

  @Override
  public void close() {
    connector.closeConnection();
  }
}
