package by.pavka.module61;

import by.pavka.module61.controller.LibraryController;
import by.pavka.module61.controller.response.LibraryResponse;
import by.pavka.module61.generator.LibraryFiller;
import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.impl.SQLBookListDao;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookServiceException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

// Another way of testing
public class Main {

  private static final LibraryController LIBRARY_CONTROLLER = LibraryController.getInstance();
  private static final String URL = "jdbc:mysql://localhost:3306/library?useLegacyDatetimeCode=false&serverTimezone=UTC";

  public static void main(String[] args) throws SQLException {

//    String request = "A#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
//    receiveAndDisplayResponse(request);
//
//    try {
//      LibraryFiller.fillArrayLibrary();
//    } catch (LibraryModelException e) {
//      e.printStackTrace();
//    }
//
//    request = "L";
//    receiveAndDisplayResponse(request);
//
//    request = "F#Y#1936";
//    receiveAndDisplayResponse(request);
//
//    request = "S#N";
//    receiveAndDisplayResponse(request);
//
//    request = "R#Незнайка в Солнечном Городе:Н.Носов:Детская Литература:1965:180";
//    receiveAndDisplayResponse(request);
//
//    request = "I#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
//    receiveAndDisplayResponse(request);
//
//    request = "R#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
//    receiveAndDisplayResponse(request);
//
//    request = "L";
//    receiveAndDisplayResponse(request);
//
//    request = "S#A";
//    receiveAndDisplayResponse(request);
//
//    request = "R#Sonnets:А.Пушкин:Зарубежная Литература:1987:102";
//    receiveAndDisplayResponse(request);
    try {
      LibraryFiller.fillSQLLibrary();
      BookListDao dao = new SQLBookListDao();
//      Book book = new Book("Drei Kameraden", new String[]{"E.M.Remarque"},
//          "Gyldendal", 1936, 352);
//      dao.includeBook(book);
//      Book book1 = new Book("Drei Kameraden", new String[]{"E.M.Remarque"},
//          "Gyldendal", 1936, 135);
//      System.out.println(dao.containsBook(book));
//      dao.removeBook(book);
//      System.out.println(dao.containsBook(book));

      List<Book> books = dao.listAllBooks();
      System.out.println(books);
      books = dao.findBooksByPublisher("Зарубежная Литература");
      System.out.println(books);
      books = dao.findBooksByAuthors(new String[]{"И.Ильф", "Е.Петров"});
      System.out.println(books);
      books = dao.findBooksByYear(1936);
      System.out.println(books);
      books = dao.sortBooksByTitle();
      System.out.println(books);
      dao.close();
    } catch (LibraryModelException | BookServiceException e) {
      e.printStackTrace();
    }
  }

  private static void receiveAndDisplayResponse(String request) {
    LibraryResponse response = LIBRARY_CONTROLLER.doRequest(request);
    System.out.println(
        response.getResult()
            + " "
            + response.getOperation()
            + " "
            + Arrays.toString(response.getExceptionInfo()));
    System.out.println(response.getBooks());
  }
}

