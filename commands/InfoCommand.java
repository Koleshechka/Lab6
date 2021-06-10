package commands;

import tools.*;

/**
 * Класс-команда Info.
 * @author Koleshechka
 */
public class InfoCommand extends Command{
    /**
     * Выводит информацию о коллекции.
     * @param console
     */

    @Override
    public String doit(Console console) {
        return(console.info());
    }
}
