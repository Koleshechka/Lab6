package commands;

import tools.*;

/**
 * Класс-команда Clear.
 * @author Koleshechka
 */
public class ClearCommand extends Command {
    /**
     * Очищает коллекцию.
     * @param console
     */

    @Override
    public String doit(Console console) {
        return(console.clear());
    }
}
