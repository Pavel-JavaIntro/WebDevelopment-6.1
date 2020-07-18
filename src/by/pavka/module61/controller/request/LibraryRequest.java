package by.pavka.module61.controller.request;

import by.pavka.module61.controller.type.BookTagType;
import by.pavka.module61.controller.type.LibraryCommandType;

public class LibraryRequest {
  private final LibraryCommandType commandType;
  private final BookTagType tagType;
  private final String data;

  public LibraryRequest(LibraryCommandType commandType, BookTagType tagType, String data) {
    this.commandType = commandType;
    this.tagType = tagType;
    this.data = data;
  }

  public LibraryCommandType getCommandType() {
    return commandType;
  }

  public BookTagType getTagType() {
    return tagType;
  }

  public String getData() {
    return data;
  }
}
