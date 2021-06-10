package commands;

import tools.Console;

/**
 * Класс-команда print_unique_price.
 * @author Koleshcehka
 */
public class PrintPriceCommand extends Command{
    /**
     * Выводит уникальные значения поля price всех элементов коллекции.
     * @param console
     */

    @Override
    public String doit(Console console) {
        return console.getPrice();
    }
}
