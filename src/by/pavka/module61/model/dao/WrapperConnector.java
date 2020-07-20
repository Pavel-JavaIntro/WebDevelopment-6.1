package by.pavka.module61.model.dao;

import java.sql.*;

public class WrapperConnector {
  private Connection connection;

  public WrapperConnector() throws DaoException {
    connection = ConnectionCreator.createConnection();
  }

  public Statement obtainStatement() throws DaoException {
    if (connection != null) {
      Statement statement;
      try {
        statement = connection.createStatement();
        if (statement != null) {
          return statement;
        }
        throw new DaoException("Statement is null");

      } catch (SQLException e) {
        throw new DaoException("Not obtained statement", e);
      }
    }
    throw new DaoException("Connection is null");
  }

  public PreparedStatement obtainPreparedStatement(String sql) throws DaoException {
    if (connection != null) {
      PreparedStatement statement;
      try {
        statement = connection.prepareStatement(sql);
        if (statement != null) {
          return statement;
        }
        throw new DaoException("Statement is null");

      } catch (SQLException e) {
        throw new DaoException("Not obtained statement", e);
      }
    }
    throw new DaoException("Connection is null");
  }

  public void closeStatement(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        System.out.println("Statement not closed");
      }
    }
  }

  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        System.out.println("Connection not closed");
      }
    }
  }
}
