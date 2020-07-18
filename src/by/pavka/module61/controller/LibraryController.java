package by.pavka.module61.controller;

import by.pavka.module61.controller.command.LibraryCommand;
import by.pavka.module61.controller.request.LibraryRequest;
import by.pavka.module61.controller.response.LibraryResponse;
import by.pavka.module61.model.entity.book.Book;

import java.util.List;

public class LibraryController {
  private static LibraryController instance;

  private LibraryController() {
  }

  public static LibraryController getInstance() {
    if (instance == null) {
      instance = new LibraryController();
    }
    return instance;
  }

  public LibraryResponse doRequest(String input) {
    LibraryRequestCreator requestCreator = new LibraryRequestCreator();
    LibraryResponse response = new LibraryResponse();
    response.setResult(LibraryResponse.RESULT_NOT_OK);
    response.setOperation(LibraryResponse.OPERATION_NOT_DEFINED);
    List<Book> books;
    try {
      LibraryRequest request = requestCreator.interpretInput(input);
      LibraryCommand libraryCommand = request.getCommandType().getCommand();
      books = libraryCommand.execute(request);
      response.setOperation(request.getCommandType().toString());
      response.setResult(LibraryResponse.RESULT_OK);
      response.setBooks(books);
    } catch (LibraryControllerException e) {
      response.setExceptionInfo(e.getStackTrace());
    }
    return response;
  }
}

