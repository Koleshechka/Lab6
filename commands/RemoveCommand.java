package commands;

import tools.*;

/**
 * Класс-команда remove_by_id.
 * @author Koleshechka
 */
public class RemoveCommand extends Command {
    /**
     * Удаляет элемент коллекции с заданным id.
     * @param console
     * @param arg
     */

    String arg;

    public RemoveCommand(String arg) {
        this.arg = arg;
    }

    public String remove(Console console, String arg) {
        try {
            Long id = Long.parseLong(arg);
            if (console.removeId(id)) {
                return("Элемент по заданному id удален.");
            }
            else return("В коллекции нет элемента с таким id.");
        } catch (NumberFormatException e) {
            return("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }


    @Override
    public String doit(Console console) {
        return(remove(console, arg));
    }
}
