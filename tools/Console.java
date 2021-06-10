package tools;

import client.Adding;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.*;
import product.Product;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс для работы с коллекцией.
 *
 * @author Koleshechka
 */

public class Console {
    private final LinkedList<Product> collection = new LinkedList<>();
    private ZonedDateTime collectionDate = ZonedDateTime.now();


    /**
     * Конструктор класса консоли.
     * Создает объект консоли, создает объект парсера для получания значений из json-файла.
     * Получает время создания коллекции.
     * @param path
     */
    public Console(String path) {
        JsonParser jsonParser = new JsonParser(path);
        collection.addAll(jsonParser.reading());
        try {
            LinkedList<ZonedDateTime> dateList = new LinkedList<>();
            for (Product product : collection) {
                dateList.add(product.getCreationDate());
            }
            collectionDate = Collections.min(dateList);
        } catch (Exception e) {
            System.out.println(" ");
        }
    }

    public Console() {
        collectionDate = ZonedDateTime.now();
    }


    public String read(String s, Scanner scanner) {
        String[] commandAll = s.split(" ");
        String command = commandAll[0];
        try {
            switch (command) {
                case "help": {
                    HelpCommand helpCommand = new HelpCommand();
                    return helpCommand.doit(this);
                }
                case "info": InfoCommand infoCommand = new InfoCommand();return infoCommand.doit(this);
                case "show": ShowCommand showCommand = new ShowCommand();return showCommand.doit(this);
                case "add": AddCommand addCommand = Adding.adding(scanner);return addCommand.doit(this);
                case "update": UpdateCommand updateCommand = new UpdateCommand(commandAll[1], Adding.adding(scanner));return updateCommand.doit(this);
                case "remove_by_id": RemoveCommand removeCommand = new RemoveCommand(commandAll[1]);return removeCommand.doit(this);
                case "clear": ClearCommand clearCommand = new ClearCommand();return clearCommand.doit(this);
                case "remove_at": RemoveAtCommand removeAtCommand = new RemoveAtCommand(commandAll[1]);return removeAtCommand.doit(this);
                case "remove_first": RemoveFirstCommand removeFirstCommand = new RemoveFirstCommand();return removeFirstCommand.doit(this);
                case "remove_all_by_price": RemovePrice removePrice = new RemovePrice(commandAll[1]);return removePrice.doit(this);
                case "print_unique_price": PrintPriceCommand printPriceCommand = new PrintPriceCommand();return printPriceCommand.doit(this);
                case "print_ascending": PrintAscendingCommand printAscendingCommand = new PrintAscendingCommand();return printAscendingCommand.doit(this);
                case "execute_script": ExecuteCommand executeCommand = new ExecuteCommand(commandAll[1]);return executeCommand.doit(this);
                case "sort": SortCommand sortCommand = new SortCommand();return sortCommand.doit(this);
                default: {
                    System.out.println("Неверная команда. Введите help для справки по доступным командам.");
                    return null;
                }
            }
        }catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("Введите необходимый аргумент.");
            return null;
        }
    }


    /**
     * Метод добавляет новый элемент в коллекцию.
     * @param product
     */
    public void addToCollection(Product product) {
        this.collection.add(product);
    }

    /**
     * Метод отображение информации о коллекции.
     * @return
     */
    public String info() {
        return "LinkedList <Product> collection, "+ DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(collectionDate)+", "+ collection.size() + " elements.";
    }

    /**
     * Метод отображения коллекции.
     * Возвращает строку с элементами коллекции.
     * @return
     */
    public String show() {
        if (collection.size()==0) {
            return("В коллекции нет элементов.");
        }else {
            StringBuilder s = new StringBuilder();
            for (Product product : collection) {
                s.append(product.toString());
            }
            return s.toString();
        }
    }

    /**
     * Метод сортировки коллекции в естественном порядке.
     * @return
     */
    public String productSort() {
        Collections.sort(collection);
        return "Коллекция отсортирована в естественном порядке.";
    }

    /**
     * Метод вывода элементов коллекции в порядке возрастания.
     * @return
     */
    public String printAscending() {
        Comparator<Product> productComparator = new ProductComparator();
        Collections.sort(collection, productComparator);
        return show();
    }

    /**
     * Удаляет элемент коллекции по его id.
     * @param id
     * @return
     */
    public boolean removeId(long id) {
        int i = 0;
        for (Product product : collection) {
            if (product.getId() == id) {
                return removeIndex(i);
            }
            i++;
        }
        return false;
    }

    /**
     * Удаляет все элементы коллекции.
     */
    public String clear() {
        if (collection.size() != 0) {
            while (collection.size() > 0) {
                removeIndex(0);
            }
            return ("Все элементы коллекции удалены.");
        }
        else return("В коллекции и так нет элементов.");
    }

    /**
     * Удаляет элемент коллекции по его индексу.
     * @param index
     * @return
     */
    public boolean removeIndex(int index) {
        if (index < collection.size()) {
            collection.get(index).removeByID();
            collection.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Удаляет первый элеменрт коллекции.
     * @return
     */
    public boolean removeFirst(){
        return removeIndex(0);
    }

    /**
     * Удаляет все элементы коллекции, имеющие заданнную цену.
     * @param price
     * @return
     */
    public boolean removePrice(Double price) {
        int i = 0;
        boolean isPrice = false;
        for(Product product : collection) {
            if (product.getPrice().equals(price)) {
                removeIndex(i);
                i--;
                isPrice = true;
            }
            i++;
        }
        return isPrice;
    }

    /**
     * Выводит уникальные значения цены элементов.
     */
    public String getPrice() {

        HashSet<Double> priceSet = new HashSet<>();
        for (Product product : collection) {
            priceSet.add(product.getPrice());
        }
        if (priceSet.size() != 0) {
            return (priceSet.toString());
        } else return ("В коллекции нет элементов.");
    }

    /**
     * Метод для сохранения коллекции в файл.
     * @throws IOException
     */
    public void writing() throws IOException {
        try {
            OutputStream g = new FileOutputStream("output.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            OutputStreamWriter gg = new OutputStreamWriter(g);
            gg.write(mapper.writeValueAsString(collection));
            gg.close();
            System.out.println("Коллекция сохранена в файл.");
        }catch(Exception e) {
            System.out.println("Невозможно сохранить коллекцию в файл.");
        }
    }

    /**
     * Метод для интерактивного чтения команд.
     * @param stream
     */

    public void reading(InputStream stream) {
        String command;
        Scanner scanner = new Scanner(stream);
        try {
            while (scanner.hasNextLine()) {
                command = scanner.nextLine();
                read(command, scanner);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
