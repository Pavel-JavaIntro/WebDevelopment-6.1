package by.pavka.module61;

import by.pavka.module61.controller.LibraryController;
import by.pavka.module61.controller.response.LibraryResponse;
import by.pavka.module61.generator.LibraryFiller;
import by.pavka.module61.model.LibraryModelException;

import java.util.Arrays;

// Another way of testing
public class  Main {

    private static final LibraryController LIBRARY_CONTROLLER = LibraryController.getInstance();

    public static void main(String[] args) {

        String request = "A#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
        receiveAndDisplayResponse(request);

        try {
            LibraryFiller.fillLibrary();
        } catch (LibraryModelException e) {
            e.printStackTrace();
        }

        request = "L";
        receiveAndDisplayResponse(request);

        request = "F#Y#1936";
        receiveAndDisplayResponse(request);

        request = "S#N";
        receiveAndDisplayResponse(request);

        request = "R#Незнайка в Солнечном Городе:Н.Носов:Детская Литература:1965:180";
        receiveAndDisplayResponse(request);

        request = "I#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
        receiveAndDisplayResponse(request);

        request = "R#Незнайка на Луне:Н.Носов:Детская Литература:1965:180";
        receiveAndDisplayResponse(request);

        request = "L";
        receiveAndDisplayResponse(request);

        request = "S#A";
        receiveAndDisplayResponse(request);

        request = "R#Sonnets:А.Пушкин:Зарубежная Литература:1987:102";
        receiveAndDisplayResponse(request);
    }

    private static void receiveAndDisplayResponse(String request) {
        LibraryResponse response = LIBRARY_CONTROLLER.doRequest(request);
        System.out.println(
                response.getResult()
                        + " "
                        + response.getOperation()
                        + " "
                        + Arrays.toString(response.getExceptionInfo()));
        System.out.println(response.getBooks());
    }
}

