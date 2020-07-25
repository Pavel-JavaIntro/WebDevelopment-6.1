package by.pavka.module61.model.dao.impl;

import by.pavka.module61.model.dao.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConnectionCreator {
  private static final String URL = "url";
  private static final String USER = "user";
  private static final String PASS = "pass";
  private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS books" +
      "(id INTEGER AUTO_INCREMENT," +
      "title VARCHAR(150)," +
      "authors VARCHAR(150)," +
      "publisher VARCHAR(100)," +
      "year INTEGER," +
      "pages INTEGER," +
      "PRIMARY KEY (id))";

  static {
    try (Connection connection = createConnection(); Statement statement = connection.createStatement()) {
      statement.execute(CREATE_TABLE);
    } catch (SQLException e) {
      // This imitates logging
      System.out.println("Table cannot be created");
      e.printStackTrace();
    } catch (DaoException e) {
      // This imitates logging
      System.out.println("Connection cannot be created");
      e.printStackTrace();
    }
  }

  public static Connection createConnection() throws DaoException {
    try {
      ResourceBundle resourceBundle = ResourceBundle.getBundle("datares.database");
      String url = resourceBundle.getString(URL);
      String user = resourceBundle.getString(USER);
      String pass = resourceBundle.getString(PASS);
      return DriverManager.getConnection(url, user, pass);
    } catch (MissingResourceException e) {
      throw new DaoException("Properties file is missed or malformed", e);
    } catch (SQLException e) {
      throw new DaoException("Not obtained connection", e);
    }
  }
}
