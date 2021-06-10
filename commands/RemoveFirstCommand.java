package commands;

import tools.*;

/**
 * Класс-команда remove_first.
 * @author Koleshechka
 */
public class RemoveFirstCommand extends Command{
    /**
     * Удаляет первый элемент коллекции.
     * @param console
     */
    public String removeFirst(Console console) {
        if (console.removeFirst()) {
            return("Первый элемент коллекции удален.");
        }
        else return("В коллекции нет элементов.");
    }

    @Override
    public String doit(Console console) {
        return(removeFirst(console));
    }
}
