package commands;

import tools.Console;

/**
 * Класс-команда remove-at.
 * @author Koleshechka
 */
public class RemoveAtCommand extends Command{
    String index;

    public RemoveAtCommand(String arg) {
        this.index = arg;
    }
    /**
     * Удаляет элемент, аходящийся в заданной позиции.
     * @param console
     * @param arg
     */

    public String removeAt(Console console, String arg) {
        try {
            int index = Integer.parseInt(arg);
            if (console.removeIndex(index)) {
                return ("Элемент по заданному индексу удален.");
            }
            else return ("В коллекции нет элемента с таким индексом.");
        } catch (NumberFormatException e) {
            return("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }

    @Override
    public String doit(Console console) {
        return(removeAt(console, index));
    }
}
