package by.pavka.module61.controller.type;

import by.pavka.module61.controller.command.LibraryCommand;
import by.pavka.module61.controller.command.impl.*;

public enum LibraryCommandType {
    ADD(new AddCommand()),
    INCLUDE(new IncludeCommand()),
    REMOVE(new RemoveCommand()),
    EXCLUDE(new ExcludeCommand()),
    LIST_ALL(new ListAllCommand()),
    SORT(new SortCommand()),
    FIND(new FindCommand());

    private LibraryCommand command;

    LibraryCommandType(LibraryCommand command) {
        this.command = command;
    }

    public LibraryCommand getCommand() {
        return command;
    }
}