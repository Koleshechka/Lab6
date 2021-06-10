package commands;

import java.util.Scanner;

/**
 * Вспомогательный класс для чтения значений при интерактивного добавлении нового элемента в коллекцию.
 * @author Koleshechka
 */
public class ReadCommand {
    public static String readForAdd(Scanner scanner) {
        return scanner.nextLine();
    }
}
