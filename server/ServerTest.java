package server;

import commands.SaveCommand;
import tools.Console;

import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerTest {

    public static int PORT = 5000;
    public static LinkedList<ServerSomethingTest> serverList = new LinkedList<>();
    public static Console serverConsole;
    private static ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        () -> {
                            try {
                                SaveCommand.save(serverConsole);
                            } catch (IOException e) {
                                System.out.println("не получилось");
                            }
                        }
                )
        );

        //String path = "/Users/svetlana/Documents/GitHub/Lab5/lab5maven/src/main/java/input.json";
        String path ="";
        try {
            path = args[0];
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Аргумент - имя файла не найден.");

        }


        try {
            System.out.println("Введите порт: ");
            PORT = Integer.parseInt(new Scanner(System.in).nextLine());
            if (PORT <= 1024) throw new NumberFormatException();
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Некорректное значение, устанавливаем значение по умолчанию (5000)");
            PORT = 5000;
        }

        serverConsole = new Console(path);

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);

        System.out.println("Server created!");
        SocketChannel socketChannel;


        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                if (System.in.available() != 0 && scanner.hasNextLine() && scanner.nextLine().equals("save")) {
                    System.out.println("read save");
                    SaveCommand.save(serverConsole);
                    continue;
                }

                socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) continue;
                serverList.add(new ServerSomethingTest(socketChannel, serverConsole));
            }
        }finally {
            serverSocketChannel.close();
        }
    }
}