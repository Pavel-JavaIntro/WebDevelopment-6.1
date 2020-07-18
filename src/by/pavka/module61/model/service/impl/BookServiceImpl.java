package by.pavka.module61.model.service.impl;

import by.pavka.module61.model.LibraryModelException;
import by.pavka.module61.model.dao.BookListDao;
import by.pavka.module61.model.dao.impl.BookListDaoImpl;
import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.service.BookService;
import by.pavka.module61.model.service.BookServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Book> addBook(String[] bookData) throws BookServiceException {
        Book book = constructBook(bookData);
        BookListDao bookListDao = new BookListDaoImpl();
        try {
            bookListDao.addBook(book);
        } catch (LibraryModelException e) {
            throw new BookServiceException("Caught CRUD exception while adding book", e);
        }
        List<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }

    @Override
    public List<Book> includeBook(String[] bookData) throws BookServiceException {
        Book book = constructBook(bookData);
        BookListDao bookListDao = new BookListDaoImpl();
        List<Book> books = new ArrayList<>();
        if (bookListDao.includeBook(book)) {
            books.add(book);
        }
        return books;
    }

    @Override
    public List<Book> removeBook(String[] bookData) throws BookServiceException {
        Book book = constructBook(bookData);
        BookListDao bookListDao = new BookListDaoImpl();
        try {
            bookListDao.removeBook(book);
        } catch (LibraryModelException e) {
            throw new BookServiceException("Caught CRUD exception while removing book", e);
        }
        List<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }

    @Override
    public List<Book> excludeBook(String[] bookData) throws BookServiceException {
        Book book = constructBook(bookData);
        BookListDao bookListDao = new BookListDaoImpl();
        List<Book> books = new ArrayList<>();
        if (bookListDao.excludeBook(book)) {
            books.add(book);
        }
        return books;
    }

    private Book constructBook(String[] bookData) throws BookServiceException {
        String title = bookData[0];
        String[] authors = bookData[1].split(",");
        String publisher = bookData[2];
        String yearString = bookData[3];
        String pageString = bookData[4];
        int yearOfPublication = verifyYear(yearString);
        int numberOfPages = verifyPages(pageString);
        Book book;
        try {
            book = new Book(title, authors, publisher, yearOfPublication, numberOfPages);
        } catch (LibraryModelException e) {
            throw new BookServiceException("Book without title", e);
        }
        return book;
    }

    private int verifyYear(String yearString) throws BookServiceException {
        int yearOfPublication;
        try {
            yearOfPublication = Integer.parseInt(yearString.trim());
        } catch (NumberFormatException e) {
            throw new BookServiceException("Wrong yearOfPublication format");
        }
        if (yearOfPublication > LocalDate.now().getYear()) {
            throw new BookServiceException("Wrong yearOfPublication");
        }
        return yearOfPublication;
    }

    private int verifyPages(String pageString) throws BookServiceException {
        int numberOfPages;
        try {
            numberOfPages = Integer.parseInt(pageString.trim());
        } catch (NumberFormatException e) {
            throw new BookServiceException("Wrong numberOPages format");
        }
        if (numberOfPages < 1) {
            throw new BookServiceException("Wrong numberOPages");
        }
        return numberOfPages;
    }

    @Override
    public List<Book> listAllBooks() {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.listAllBooks();
    }

    @Override
    public List<Book> sortByTitle() {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.sortBooksByTitle();
    }

    @Override
    public List<Book> sortByAuthors() {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.sortBooksByAuthors();
    }

    @Override
    public List<Book> sortByPublisher() {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.sortBooksByPublisher();
    }

    @Override
    public List<Book> sortByYear() {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.sortBooksByYear();
    }

    @Override
    public List<Book> sortByNumberOfPages() {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.sortBooksByNumberOfPages();
    }

    @Override
    public List<Book> findByTitle(String searchValue) {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.findBooksByTitle(searchValue);
    }

    @Override
    public List<Book> findByAuthors(String[] searchValue) {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.findBooksByAuthors(searchValue);
    }

    @Override
    public List<Book> findByPublisher(String searchValue) {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.findBooksByPublisher(searchValue);
    }

    @Override
    public List<Book> findByYear(String searchValue) throws BookServiceException {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.findBooksByYear(verifyYear(searchValue));
    }

    @Override
    public List<Book> findByNumberOfPages(String searchValue) throws BookServiceException {
        BookListDao bookListDao = new BookListDaoImpl();
        return bookListDao.findBooksByNumberOfPages(verifyPages(searchValue));
    }
}

