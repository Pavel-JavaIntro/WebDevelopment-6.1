package by.pavka.module61.controller.response;

import by.pavka.module61.model.entity.book.Book;

import java.util.List;

public class LibraryResponse {
    public static final String RESULT_OK = "OK";
    public static final String RESULT_NOT_OK = "NOT OK";
    public static final String OPERATION_NOT_DEFINED = "Operation not defined";

    private String result;
    private StackTraceElement[] exceptionInfo;
    private String operation;
    private List<Book> books;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public StackTraceElement[] getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(StackTraceElement[] exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
