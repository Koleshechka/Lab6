package commands;

import tools.Console;

/**
 * Класс-команда remove_all_by_price.
 * @author Koleshechka
 */
public class RemovePrice extends Command{

    String price;

    public RemovePrice(String arg) {
        this.price = arg;
    }
    /**
     * Удаляет все элементы коллекции,значения поля price которых эквивалентны заданному.
     * @param console
     * @param arg
     */
    public String removePrice(Console console, String arg){
        try {
            Double price = Double.parseDouble(arg);
            if (console.removePrice(price)) {
                return("Элементы с заданной ценой удалены.");
            }
            else return("В коллекции нет элементов с заданной ценой.");
        } catch (NumberFormatException e) {
            return("Неправильный формат аргумента.");
        }
    }

    @Override
    public String doit(Console console) {
        return(removePrice(console, price));
    }
}
