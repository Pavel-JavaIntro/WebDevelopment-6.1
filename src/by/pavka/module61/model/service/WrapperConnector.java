package by.pavka.module61.model.service;

import java.sql.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WrapperConnector {
  private Connection connection;

  public WrapperConnector() throws BookServiceException {
    try {
      ResourceBundle resourceBundle = ResourceBundle.getBundle("datares.database");
      String url = resourceBundle.getString("url");
      String user = resourceBundle.getString("user");
      String pass = resourceBundle.getString("pass");
      connection = DriverManager.getConnection(url, user, pass);
    } catch (MissingResourceException e) {
      throw new BookServiceException("Properties file is missing");
    } catch (SQLException e) {
      throw new BookServiceException("Not obtained connection");
    }
  }

  public Statement obtainStatement() throws BookServiceException {
    if (connection != null) {
      Statement statement;
      try {
        statement = connection.createStatement();
        if (connection != null) {
          return statement;
        }
        throw new BookServiceException("Statement is null");

      } catch (SQLException e) {
        throw new BookServiceException("Not obtained statement", e);
      }
    }
    throw new BookServiceException("Connection is null");
  }

  public PreparedStatement obtainPreparedStatement(String sql) throws BookServiceException {
    if (connection != null) {
      PreparedStatement statement;
      try {
        statement = connection.prepareStatement(sql);
        if (connection != null) {
          return statement;
        }
        throw new BookServiceException("Statement is null");

      } catch (SQLException e) {
        throw new BookServiceException("Not obtained statement", e);
      }
    }
    throw new BookServiceException("Connection is null");
  }

  public void closeStatement(Statement statement) throws BookServiceException {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        throw new BookServiceException("Statement not closed", e);
      }
    }
  }

  public void closeConnection() throws BookServiceException {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new BookServiceException("Statement not closed", e);
      }
    }
  }
}
