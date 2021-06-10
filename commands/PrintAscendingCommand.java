package commands;

import tools.Console;

/**
 * Класс-команда print_ascending.
 * @author Koleshechka
 */
public class PrintAscendingCommand extends Command{
    /**
     * Выводит элементы коллекции в порядке возрастания.
     * @param console
     */

    @Override
    public String doit(Console console) {
        return console.printAscending();
    }
}
