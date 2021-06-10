package client;

import commands.*;

import java.io.InputStream;
import java.util.Scanner;

public class Listener {

    public static Command read(String s, Scanner scanner) {
        String[] commandAll = s.split(" ");
        String command = commandAll[0];
        try {
            switch (command) {
                case "help": {
                    HelpCommand helpCommand = new HelpCommand();
                    return helpCommand;
                }
                case "info": InfoCommand infoCommand = new InfoCommand();return infoCommand;
                case "show": ShowCommand showCommand = new ShowCommand();return showCommand;
                case "add": AddCommand addCommand = Adding.adding(scanner);return addCommand;
                case "update": UpdateCommand updateCommand = new UpdateCommand(commandAll[1], Adding.adding(scanner));return updateCommand;
                case "remove_by_id": RemoveCommand removeCommand = new RemoveCommand(commandAll[1]);return removeCommand;
                case "clear": ClearCommand clearCommand = new ClearCommand();return clearCommand;
                case "remove_at": RemoveAtCommand removeAtCommand = new RemoveAtCommand(commandAll[1]);return removeAtCommand;
                case "remove_first": RemoveFirstCommand removeFirstCommand = new RemoveFirstCommand();return removeFirstCommand;
                case "remove_all_by_price": RemovePrice removePrice = new RemovePrice(commandAll[1]);return removePrice;
                case "print_unique_price": PrintPriceCommand printPriceCommand = new PrintPriceCommand();return printPriceCommand;
                case "print_ascending": PrintAscendingCommand printAscendingCommand = new PrintAscendingCommand();return printAscendingCommand;
                case "execute_script": ExecuteCommand executeCommand = new ExecuteCommand(commandAll[1]);return executeCommand;
                case "sort": SortCommand sortCommand = new SortCommand();return sortCommand;
                case "exit": System.exit(0);return null;
                default: {
                    System.out.println("Неверная команда. Введите help для справки по доступным командам.");
                    return null;
                }
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Введите необходимый аргумент.");
            return null;
        }
    }
}
