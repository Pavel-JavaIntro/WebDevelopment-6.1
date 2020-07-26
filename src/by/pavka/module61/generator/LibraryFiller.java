package by.pavka.module61.generator;

import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.DaoException;
import by.pavka.module61.model.dao.impl.SqlBookListDao;
import by.pavka.module61.model.dao.impl.WrapperConnector;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.entity.library.Library;
import by.pavka.module61.model.entity.library.impl.LibraryImpl;
import by.pavka.module61.model.LibraryModelException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryFiller {
  private static final String CLEAN_LIBRARY = "DELETE FROM books";

  private LibraryFiller() {}

  public static void fillSqlLibrary
      () throws LibraryModelException {
    cleanSqlLibrary();
    BookListDao dao = new SqlBookListDao();
    Book book1 = new Book("Drei Kameraden", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352);
    Book book2 =
        new Book("12 стульев", new String[]{"И.Ильф", "Е.Петров"}, "Тридцать дней", 1928, 290);
    Book book3 = new Book("Nature №6822", null, "Nature Publishing Group", 2001, 921);
    Book book4 = new Book("Нецензурные антисоветские анекдоты", null, null, 1979, 32);
    Book book5 =
        new Book("Sonnets", new String[]{"W.Shakespeare"}, "Зарубежная Литература", 1987, 102);

    try {
      dao.addBook(book1);
      dao.addBook(book2);
      dao.addBook(book3);
      dao.addBook(book4);
      dao.addBook(book5);
    } finally {
      dao.close();
    }
  }

  private static void cleanSqlLibrary() throws LibraryModelException {
    WrapperConnector connector;
    try {
      connector = new WrapperConnector();
    } catch (DaoException e) {
      throw new LibraryModelException("Connector not created", e);
    }
    try (PreparedStatement statement = connector.obtainPreparedStatement(CLEAN_LIBRARY)) {
      statement.execute();
    } catch (SQLException | DaoException e) {
      throw new LibraryModelException("Library not cleaned", e);

    }
  }

  // This outdated method is inherited from the previous "Books and Library App" implementation and
  // can be ignored now
  public static void fillArrayLibrary() throws LibraryModelException {
    Library library = LibraryImpl.getInstance();
    Book book1 = new Book("Drei Kameraden", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352);
    Book book2 =
        new Book("12 стульев", new String[]{"И.Ильф", "Е.Петров"}, "Тридцать дней", 1928, 290);
    Book book3 = new Book("Nature №6822", null, "Nature Publishing Group", 2001, 921);
    Book book4 = new Book("Нецензурные антисоветские анекдоты", null, null, 1979, 32);
    Book book5 =
        new Book("Sonnets", new String[]{"W.Shakespeare"}, "Зарубежная Литература", 1987, 102);
    library.insert(book1);
    library.insert(book2);
    library.insert(book3);
    library.insert(book4);
    library.insert(book5);
  }
}

