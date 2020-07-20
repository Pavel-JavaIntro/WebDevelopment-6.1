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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("datares.database");
    String url = resourceBundle.getString(URL);
    String user = resourceBundle.getString(USER);
    String pass = resourceBundle.getString(PASS);
    try {
      Connection connection = DriverManager.getConnection(url, user, pass);
      if (connection != null) {
        Statement statement = null;
        try {
          statement = connection.createStatement();
          statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
          System.err.println("Все кончено. Мы погибли. Таблица не создается");
        }
        connection.close();
      }
    } catch (SQLException | MissingResourceException e) {
        System.err.println("Все кончено. Мы погибли. Связи с Хьюстоном нет");
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
      throw new DaoException("Properties file is missing", e);
    } catch (SQLException e) {
      throw new DaoException("Not obtained connection", e);
    }
  }
}
