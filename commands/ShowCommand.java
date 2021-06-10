package commands;

import tools.Console;

/**
 * Класс-команда show.
 * @author Koleshechka
 */
public class ShowCommand extends Command{
    /**
     * Выводит элементы коллекции.
     * @param console
     */

    @Override
    public String doit(Console console) {
        return (console.show());
    }
}
