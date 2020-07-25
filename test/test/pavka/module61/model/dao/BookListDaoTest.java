package test.pavka.module61.model.dao;

import by.pavka.module61.generator.LibraryFiller;
import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.impl.SqlBookListDao;
import by.pavka.module61.model.entity.book.Book;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class BookListDaoTest {
  private BookListDao dao;

  @BeforeMethod
  public void createLibrary() {
    try {
      LibraryFiller.fillSqlLibrary();
      dao = new SqlBookListDao();
    } catch (LibraryModelException e) {
      fail("LibraryFiller doesn't work");
    }
  }

  public void addBookTest1() {
    Book book;
    boolean isInLibrary = false;
    try {
      book =
          new Book("Теоретическая механика", new String[]{"Ландау", "Лифшиц"}, "Наука", 1987, 199);
      dao.addBook(book);
      isInLibrary = dao.containsBook(book);
    } catch (LibraryModelException e) {
      fail("Exception is not allowed here");
    }
    assertTrue(isInLibrary);
  }

  @Test(
      expectedExceptions = LibraryModelException.class,
      expectedExceptionsMessageRegExp = "Book not added")
  public void addBookTest2() throws LibraryModelException {
    Book book =
        new Book("Теоретическая механика", new String[]{"Ландау", "Лифшиц"}, "Наука", 1987, 199);
    dao.addBook(book);
    dao.addBook(book);
  }

  @Test(
      expectedExceptions = LibraryModelException.class,
      expectedExceptionsMessageRegExp = "Book not added")
  public void addBookTest3() throws LibraryModelException {
    dao.addBook(null);
  }

  @Test(
      expectedExceptions = LibraryModelException.class,
      expectedExceptionsMessageRegExp = "Book must have a title")
  public void addBookTest4() throws LibraryModelException {
    Book book = new Book(null, new String[]{"nn"}, null, 0, 0);
    dao.addBook(book);
  }

  public void excludeBookTest1() throws LibraryModelException {
    Book book =
        new Book("Теоретическая механика", new String[]{"Ландау", "Лифшиц"}, "Наука", 1987, 199);
    dao.addBook(book);
    dao.excludeBook(book);
    int expected = 5;
    int actual = dao.listAllBooks().size();
    assertEquals(actual, expected);
  }

  public void excludeBookTest2() throws LibraryModelException {
    Book book =
        new Book("Теоретическая механика", new String[]{"Ландау", "Лифшиц"}, "Наука", 1987, 199);
    dao.excludeBook(book);
    int expected = 5;
    int actual = dao.listAllBooks().size();
    assertEquals(actual, expected);
  }

  public void sortBooksByAuthorsTest() throws LibraryModelException {
    List<Book> expected = new ArrayList<>();
    expected.add(new Book("Nature №6822", null, "Nature Publishing Group", 2001, 921));
    expected.add(new Book("Нецензурные антисоветские анекдоты", null, null, 1979, 32));
    expected.add(new Book("Drei Kameraden", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352));
    expected.add(
        new Book("Sonnets", new String[]{"W.Shakespeare"}, "Зарубежная Литература", 1987, 102));
    expected.add(
        new Book("12 стульев", new String[]{"И.Ильф", "Е.Петров"}, "Тридцать дней", 1928, 290));
    List<Book> actual = dao.sortBooksByAuthors();
    System.out.println(actual);
    assertEquals(actual, expected);
  }

  public void sortBooksByPagesTest() throws LibraryModelException {
    List<Book> expected = new ArrayList<>();
    expected.add(new Book("Нецензурные антисоветские анекдоты", null, null, 1979, 32));
    expected.add(
        new Book("Sonnets", new String[]{"W.Shakespeare"}, "Зарубежная Литература", 1987, 102));

    expected.add(
        new Book("12 стульев", new String[]{"И.Ильф", "Е.Петров"}, "Тридцать дней", 1928, 290));
    expected.add(new Book("Drei Kameraden", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352));
    expected.add(new Book("Nature №6822", null, "Nature Publishing Group", 2001, 921));
    List<Book> actual = dao.sortBooksByNumberOfPages();
    assertEquals(actual, expected);
  }

  public void findBookByYearTest1() throws LibraryModelException {
    Book expected =
        new Book("Drei Kameraden", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352);
    Book actual = dao.findBooksByYear(1936).get(0);
    assertEquals(actual, expected);
  }

  public void findBookByYearTest2() throws LibraryModelException {
    int expected = 0;
    int actual = dao.findBooksByYear(1836).size();
    assertEquals(actual, expected);
  }

  public void containsBook1() throws LibraryModelException {
    Book book = new Book("Drei Kameraden", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352);
    assertTrue(dao.containsBook(book));
  }

  public void containsBoo2() throws LibraryModelException {
    Book book = new Book("Kamasutra", new String[]{"E.M.Remarque"}, "Gyldendal", 1936, 352);
    assertFalse(dao.containsBook(book));
  }
}
