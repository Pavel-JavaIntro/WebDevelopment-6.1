package by.pavka.module61.generator;

import by.pavka.module61.model.entity.book.Book;
import by.pavka.module61.model.entity.library.Library;
import by.pavka.module61.model.entity.library.impl.LibraryImpl;
import by.pavka.module61.model.LibraryModelException;

public class LibraryFiller {
    public static void fillLibrary() throws LibraryModelException {
        Library library = LibraryImpl.getInstance();
        Book book1 = new Book("Drei Kameraden", new String[] {"E.M.Remarque"}, "Gyldendal", 1936, 352);
        Book book2 =
                new Book("12 стульев", new String[] {"И.Ильф", "Е.Петров"}, "Тридцать дней", 1928, 290);
        Book book3 = new Book("Nature №6822", null, "Nature Publishing Group", 2001, 921);
        Book book4 = new Book("Нецензурные антисоветские анекдоты", null, null, 1979, 32);
        Book book5 =
                new Book("Sonnets", new String[] {"W.Shakespeare"}, "Зарубежная Литература", 1987, 102);
        library.insert(book1);
        library.insert(book2);
        library.insert(book3);
        library.insert(book4);
        library.insert(book5);
    }
}

