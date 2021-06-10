package commands;

import tools.Console;

/**
 * Класс-команда sort.
 * @author Koleshechka
 */
public class SortCommand extends Command{

    @Override
    public String doit(Console console) {
        return console.productSort();
    }
}
