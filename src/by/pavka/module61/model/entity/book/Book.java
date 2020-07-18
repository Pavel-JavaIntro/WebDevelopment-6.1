package by.pavka.module61.model.entity.book;

import by.pavka.module61.model.LibraryModelException;

import java.util.Arrays;

public class Book {
  private final String title;
  private final String[] authors;
  private final String publisher;
  private final int yearOfPublication;
  private final int numberOfPages;

  public Book(
      String title, String[] authors, String publisher, int yearOfPublication, int numberOfPages)
      throws LibraryModelException {
    if (title == null) {
      throw new LibraryModelException("Book must have a title");
    }
    this.title = title;
    if (authors != null) {
      this.authors = authors;
    } else {
      this.authors = new String[]{""};
    }
    if (publisher != null) {
      this.publisher = publisher;
    } else {
      this.publisher = "";
    }
    this.yearOfPublication = yearOfPublication;
    this.numberOfPages = numberOfPages;
  }

  public String getTitle() {
    return title;
  }

  public String[] getAuthors() {
    return authors;
  }

  public String getPublisher() {
    return publisher;
  }

  public int getYearOfPublication() {
    return yearOfPublication;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Book)) return false;
    Book book = (Book) o;
    return yearOfPublication == book.yearOfPublication
        && numberOfPages == book.numberOfPages
        && title.equals(book.title)
        && Arrays.equals(authors, book.authors)
        && publisher.equals(book.publisher);
  }

  @Override
  public int hashCode() {
    int res = yearOfPublication + numberOfPages + title.hashCode() + publisher.hashCode();
    res = 31 * res + Arrays.hashCode(authors);
    return res;
  }

  @Override
  public String toString() {
    return String.format(
        "Book: title = %s, author(s) = %s, publisher = %s, year = %d, pages = " + "%d",
        title, Arrays.toString(authors), publisher, yearOfPublication, numberOfPages);
  }
}

