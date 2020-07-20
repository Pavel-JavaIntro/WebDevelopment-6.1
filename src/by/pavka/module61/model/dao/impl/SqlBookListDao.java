package by.pavka.module61.model.dao.impl;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.DaoException;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.dao.WrapperConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlBookListDao implements BookListDao {
  private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS books" +
      "(id INTEGER AUTO_INCREMENT," +
      "title VARCHAR(150)," +
      "authors VARCHAR(150)," +
      "publisher VARCHAR(100)," +
      "year INTEGER," +
      "pages INTEGER," +
      "PRIMARY KEY (id))";
  private static final String ADD_BOOK = "INSERT INTO books (title, authors, publisher, year, " +
      "pages) VALUES(?, ?, ?, ?, ?)";
  private static final String REMOVE_BOOK = "DELETE FROM books WHERE title=? AND authors=? " +
      "AND publisher=? AND year=? AND pages=?";
  private static final String CONTAINS_BOOK = "SELECT title, authors, publisher, year, pages " +
      "FROM books WHERE title=? AND authors=? " +
      "AND publisher=? AND year=? AND pages=?";
  private static final String ALL = "SELECT title, authors, publisher, year, pages FROM books";
  private static final String FIND_BOOK = "SELECT title, authors, publisher, year, pages FROM books WHERE ";
  private static final String SORT_BOOK = "SELECT title, authors, publisher, year, pages FROM books ORDER BY ";

  private WrapperConnector connector;

  public SqlBookListDao() throws LibraryModelException {
    Statement statement = null;
    try {
      connector = new WrapperConnector();
      statement = connector.obtainStatement();
      statement.execute(CREATE_TABLE);
      connector.closeStatement(statement);
    } catch (DaoException e) {
      throw new LibraryModelException("DAO cannot obtain connection", e);
    } catch (SQLException e) {
      throw new LibraryModelException("Library cannot be created", e);
    } finally {
      if (statement != null) {
        connector.closeStatement(statement);
      }
    }
  }

  @Override
  public void addBook(Book book) throws LibraryModelException {
    if (!containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(ADD_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
        connector.closeStatement(statement);
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not added because of SQL or Dao exception", e);
      } finally {
        if (statement != null) {
          connector.closeStatement(statement);
        }
      }
    } else {
      throw new LibraryModelException("Book not added");
    }
  }

  @Override
  public boolean includeBook(Book book) throws LibraryModelException {
    if (!containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(ADD_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
        connector.closeStatement(statement);
        return true;
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not included because of SQL or service exception", e);
      } finally {
        if (statement != null) {
          connector.closeStatement(statement);
        }
      }
    }
    return false;
  }

  @Override
  public void removeBook(Book book) throws LibraryModelException {
    if (containsBook(book)) {
      PreparedStatement statement = null;
      try {
        statement = connector.obtainPreparedStatement(REMOVE_BOOK);
        setBookIntoStatement(book, statement);
        statement.executeUpdate();
        connector.closeStatement(statement);
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not removed because of SQL or service exception", e);
      } finally {
        if (statement != null) {
          connector.closeStatement(statement);
        }
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
        connector.closeStatement(statement);
        return true;
      } catch (DaoException | SQLException e) {
        throw new LibraryModelException("Book not removed because of SQL or service exception", e);
      } finally {
        if (statement != null) {
          connector.closeStatement(statement);
        }
      }
    }
    return false;
  }

  private void setBookIntoStatement(Book book, PreparedStatement statement) throws SQLException {
    statement.setString(1, book.getTitle());
    statement.setString(2, Arrays.toString(book.getAuthors()));
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
      connector.closeStatement(statement);
    } catch (DaoException | SQLException e) {
      throw new LibraryModelException("Caught connection or SQL exception", e);
    } finally {
      if (statement != null) {
        connector.closeStatement(statement);
      }
    }
    return result;
  }

  @Override
  public List<Book> listAllBooks() throws LibraryModelException {
    return formResultList(ALL);
  }

  private List<Book> formResultList(String sql) throws LibraryModelException {
    List<Book> resultList = new ArrayList<>();
    Statement statement = null;
    try {
      statement = connector.obtainStatement();
      try (ResultSet resultSet = statement.executeQuery(sql)) {
        if (resultSet != null) {
          while (resultSet.next()) {
            String title = resultSet.getString(2);
            String[] authors = resultSet.getString(3).split(",\\s?");
            String publisher = resultSet.getString(4);
            int year = resultSet.getInt(5);
            int pages = resultSet.getInt(6);
            Book book = new Book(title, authors, publisher, year, pages);
            resultList.add(book);
          }
        }
      }
    } catch (DaoException | SQLException e) {
      throw new LibraryModelException("Caught connection or SQL exception", e);
    } finally {
      if (statement != null) {
        connector.closeStatement(statement);
      }
    }
    return resultList;
  }

  @Override
  public List<Book> sortBooksByTitle() throws LibraryModelException {
    return formResultList(SORT_BOOK + "title");
  }

  @Override
  public List<Book> sortBooksByAuthors() throws LibraryModelException {
    return formResultList(SORT_BOOK + "authors");
  }

  @Override
  public List<Book> sortBooksByPublisher() throws LibraryModelException {
    return formResultList(SORT_BOOK + "publisher");
  }

  @Override
  public List<Book> sortBooksByYear() throws LibraryModelException {
    return formResultList(SORT_BOOK + "year");
  }

  @Override
  public List<Book> sortBooksByNumberOfPages() throws LibraryModelException {
    return formResultList(SORT_BOOK + "pages");
  }

  @Override
  public List<Book> findBooksByTitle(String title) throws LibraryModelException {
    String sql = FIND_BOOK + "title=" + title;
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByAuthors(String[] authors) throws LibraryModelException {
    String sql = FIND_BOOK + "authors='" + Arrays.toString(authors) + "'";
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByPublisher(String publisher) throws LibraryModelException {
    String sql = FIND_BOOK + "publisher='" + publisher + "'";
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByYear(int year) throws LibraryModelException {
    String sql = FIND_BOOK + "year=" + year;
    return formResultList(sql);
  }

  @Override
  public List<Book> findBooksByNumberOfPages(int pages) throws LibraryModelException {
    String sql = FIND_BOOK + "pages=" + pages;
    return formResultList(sql);
  }

  @Override
  public void close() {
    connector.closeConnection();
  }
}
