package by.pavka.module61.model.entity.library.impl;

import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.entity.library.Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryImpl implements Library {
    private static Library instance;

    private List<Book> books;

    private LibraryImpl() {
        books = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new LibraryImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Book book) {
        if (book == null || contains(book)) {
            return false;
        }
        return books.add(book);
    }

    @Override
    public boolean delete(Book book) {
        return books.remove(book);
    }

    @Override
    public boolean contains(Book book) {
        return books.contains(book);
    }

    @Override
    public List<Book> listAll() {
        return Collections.unmodifiableList(books);
    }

    // This method is for test purposes
    public void clean() {
        books = new ArrayList<>();
    }
}
