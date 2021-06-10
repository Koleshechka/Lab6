package commands;

import tools.Console;

import java.io.IOException;

/**
 * Класс-команда save.
 * @author Koleshechka
 */
public class SaveCommand {
    /**
     * Сохраняет коллекцию в файл.
     * @param console
     * @throws IOException
     */
    public static void save(Console console) throws IOException {
        console.writing();
    }
}
