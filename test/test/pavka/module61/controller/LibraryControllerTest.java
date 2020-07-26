package test.pavka.module61.controller;

import by.pavka.module61.controller.LibraryController;
import by.pavka.module61.controller.response.LibraryResponse;
import by.pavka.module61.generator.LibraryFiller;
import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.entity.book.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static by.pavka.module61.controller.response.LibraryResponse.OPERATION_NOT_DEFINED;
import static by.pavka.module61.controller.response.LibraryResponse.RESULT_NOT_OK;
import static org.testng.Assert.*;

@Test
public class LibraryControllerTest {
  private LibraryController controller = LibraryController.getInstance();

  @BeforeMethod
  public void createLibrary() {
    try {
      LibraryFiller.fillSqlLibrary();
    } catch (LibraryModelException e) {
      fail("LibraryFiller doesn't work");
    }
  }

  public void abracadabraTest1() {
    String abracadabra = "gj47xwl22j";
    LibraryResponse response = controller.doRequest(abracadabra);
    String actual = response.getResult();
    assertEquals(actual, RESULT_NOT_OK);
  }

  public void abracadabraTest2() {
    String abracadabra = "gj47xwlo";
    LibraryResponse response = controller.doRequest(abracadabra);
    String actual = response.getOperation();
    assertEquals(actual, OPERATION_NOT_DEFINED);
  }

  public void abracadabraTest3() {
    String abracadabra = "S#j47xwl33o";
    LibraryResponse response = controller.doRequest(abracadabra);
    List<Book> actual = response.getBooks();
    assertNull(actual);
  }

  public void addTest1() throws LibraryModelException {
    String input = "A#Три капитана:В.Каверин:Молодая гвардия:1987:304";
    LibraryResponse response = controller.doRequest(input);
    Book actual = response.getBooks().get(0);
    Book expected =
        new Book("Три капитана", new String[]{"В.Каверин"}, "Молодая гвардия", 1987, 304);
    assertEquals(actual, expected);
  }

  public void addTest2() throws LibraryModelException {
    String input = "A#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
    controller.doRequest(input);
    String input2 = "L";
    LibraryResponse response = controller.doRequest(input2);
    int actual = response.getBooks().size();
    int expected = 6;
    assertEquals(actual, expected);
  }

  public void includeTest1() {
    String input = "I#Три капитана:В.Каверин:Молодая гвардия:1987:304";
    controller.doRequest(input);
    LibraryResponse response = controller.doRequest(input);
    List<Book> expected = new ArrayList<>();
    List<Book> actual = response.getBooks();
    assertEquals(actual, expected);
  }

  public void includeTest2() {
    String input = "I#Три капитана:В.Каверин:Молодая гвардия:1987:304";
    controller.doRequest(input);
    String input2 = "L";
    LibraryResponse response = controller.doRequest(input2);
    int expected = 6;
    int actual = response.getBooks().size();
    assertEquals(actual, expected);
  }

  public void removeTest1() {
    String input = "R#Sonnets:W.Shakespeare:Зарубежная Литература:1987:102";
    controller.doRequest(input);
    String input2 = "L";
    LibraryResponse response = controller.doRequest(input2);
    int expected = 4;
    int actual = response.getBooks().size();
    assertEquals(actual, expected);
  }

  public void removeTest2() {
    String input = "R#Sonnets:А.Пушкин:Зарубежная Литература:1987:102";
    LibraryResponse response = controller.doRequest(input);
    String actual = response.getResult();
    assertEquals(actual, RESULT_NOT_OK);
  }

  public void excludeTest1() {
    String input = "E#Sonnets:W.Shakespeare:Зарубежная Литература:1987:102";
    controller.doRequest(input);
    String input2 = "L";
    LibraryResponse response = controller.doRequest(input2);
    int expected = 4;
    int actual = response.getBooks().size();
    assertEquals(actual, expected);
  }

  public void excludeTest2() {
    String input = "E#Незнайка в Солнечном Городе:Н.Носов:Детская Литература:1965:180";
    controller.doRequest(input);
    String input2 = "L";
    LibraryResponse response = controller.doRequest(input2);
    int expected = 5;
    int actual = response.getBooks().size();
    assertEquals(actual, expected);
  }

  public void sortByYearTest() throws LibraryModelException {
    String input = "S#Y";
    LibraryResponse response = controller.doRequest(input);
    Book expectedFirst =
        new Book("12 стульев", new String[]{"И.Ильф", "Е.Петров"}, "Тридцать дней", 1928, 290);
    Book actualFirst = response.getBooks().get(0);
    assertEquals(actualFirst, expectedFirst);
  }

  public void sortByTitleTest() throws LibraryModelException {
    String input = "S#T";
    LibraryResponse response = controller.doRequest(input);
    Book expectedLast = new Book("Нецензурные антисоветские анекдоты", null, null, 1979, 32);
    Book actualLast = response.getBooks().get(4);
    assertEquals(actualLast, expectedLast);
  }

  public void findByAuthors() throws LibraryModelException {
    String input = "F#A#W.Shakespeare";
    LibraryResponse response = controller.doRequest(input);
    Book expected =
        new Book("Sonnets", new String[]{"W.Shakespeare"}, "Зарубежная Литература", 1987, 102);

    Book actual = null;
    if (response.getBooks().size() > 0) {
      actual = response.getBooks().get(0);
    }
    assertEquals(actual, expected);
  }

  public void findByTitle() throws LibraryModelException {
    String input = "F#T#Sonnets";
    LibraryResponse response = controller.doRequest(input);
    Book expected =
        new Book("Sonnets", new String[]{"W.Shakespeare"}, "Зарубежная Литература", 1987, 102);
    Book actual = response.getBooks().get(0);
    assertEquals(actual, expected);
  }
}

