package commands;

import tools.Console;

import java.io.*;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Класс-команда execute_script.
 * @author Koleshechka
 */
public class ExecuteCommand extends Command{

    static HashSet<String> fileNames = new HashSet<>();
    String fileName;

    public ExecuteCommand(String arg) {
        this.fileName = arg;
    }

    /**
     * Выполняет скрипт из переданного файла.
     * @param console
     * @param arg
     * @throws IOException
     * @throws NoSuchElementException
     */
    public String execute(Console console, String arg) throws IOException, NoSuchElementException {
        if (fileNames.contains(arg)) {
            throw new IOException("Не надо строить циклы из script файлов!");
        }
        try {
            if (arg.equals("/dev/zero") || arg.equals("/dev/random")) {throw new NullPointerException();}
            InputStream stream = new FileInputStream(arg);
            fileNames.add(arg);
            console.reading(stream);
            fileNames.remove(arg);
            return ("Чтение script файла окончено: имя файла = " + arg);
            //stream.close();
        }catch (NoSuchElementException e) {
            return ("Не найден аргумент - имя script файла.");
        } catch (NullPointerException e) {
            return ("Введите корректный файл.");
        } catch (Exception e) {
            return ("Файл не найден.");
        }
    }

    @Override
    public String doit(Console console) throws IOException {
        return execute(console, fileName);
    }
}
