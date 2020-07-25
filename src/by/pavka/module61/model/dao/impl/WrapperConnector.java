package by.pavka.module61.model.dao.impl;

import by.pavka.module61.model.dao.DaoException;

import java.sql.*;

public class WrapperConnector {
  private Connection connection;

  public WrapperConnector() throws DaoException {
    connection = ConnectionCreator.createConnection();
  }

  public Statement obtainStatement() throws DaoException {
    if (connection != null) {
      try {
        return connection.createStatement();
      } catch (SQLException e) {
        throw new DaoException("Not obtained statement", e);
      }
    }
    throw new DaoException("Connection is null");
  }

  public PreparedStatement obtainPreparedStatement(String sql) throws DaoException {
    if (connection != null) {
      try {
        return connection.prepareStatement(sql);
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
        // This imitates logging
        System.out.println("Statement not closed");
        e.printStackTrace();
      }
    }
  }

  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        // This imitates logging
        System.out.println("Connection not closed");
        e.printStackTrace();
      }
    }
  }
}
