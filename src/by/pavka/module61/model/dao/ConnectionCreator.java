package by.pavka.module61.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConnectionCreator {
  private static final String URL = "url";
  private static final String USER = "user";
  private static final String PASS = "pass";

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
