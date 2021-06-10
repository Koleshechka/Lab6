package commands;

import tools.*;

import java.util.Scanner;

/**
 * Класс-команда update.
 * @author Koleshechka
 */
public class UpdateCommand extends Command{

    String id = null;
    AddCommand addCommand = new AddCommand();

    public UpdateCommand(String id, AddCommand addCommand) {
        this.id = id;
        this.addCommand = addCommand;
    }
    /**
     * Обновляет элемент коллекции, id которого равен заданному.
     * @param console
     * @param arg
     */
    public String update(Console console, String arg) {
        try {
            long id = Long.parseLong(arg);
            if (console.removeId(id)) {
                addCommand.add(console, addCommand.getName(), addCommand.getX(), addCommand.getY(), addCommand.getPrice(), addCommand.getUnitOfMeasure(), addCommand.getNameOfOrg(), addCommand.getAnnualTurnover(), addCommand.getType(), id);
                return("Элемент с таким id существует в коллекции. Мы успешно его обновили.");
            }
            else return("В коллекции нет элемента с таким id. Попробуйте еще раз.");
        } catch (NumberFormatException e) {
            return("Неправильный формат аргумента. Попробуйте еще раз.");
        }
    }

    @Override
    public String doit(Console console) {
        return update(console, id);
    }
}
