package client;


import commands.Command;
import server.ServerResult;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CLientTest {

    private int PORT = 5000;
    private static CLientTest client;
    private SocketChannel socketChannel;

    public static void main(String[] args) throws InterruptedException {
        client = new CLientTest();
        client.searchPort();
    }

    public CLientTest(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public CLientTest () {}

    public void searchPort() throws InterruptedException {
        try {
            System.out.println("Введите порт: ");
            PORT = Integer.parseInt(new Scanner(System.in).nextLine());
            if (PORT <= 1024) throw new NumberFormatException();
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("Некорректное значение, устанавливаем значение по умолчанию (5000)");
            PORT = 5000;
        }
        client.connect(PORT);
    }

    public void connect(int PORT) throws InterruptedException {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(PORT));

            client = new CLientTest(socketChannel);
            System.out.println("Мы успешно подключились к серверу!");
            client.speak(client);
        } catch (IOException e) {
            System.out.println("Мы не можем подключиться к серверу по указанному порту. Давайте попробуем еще раз.");
            client.searchPort();
        }
    }

    public void speak(CLientTest client) throws IOException, InterruptedException {
        while (true) {
            String command;
            Scanner scanner = new Scanner(System.in);
            try {
                while (scanner.hasNextLine()) {
                    command = scanner.nextLine();
                    Command now = Listener.read(command, scanner);
                    if (now == null) {
                        continue;
                    } else {
                        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(4096); // buff size
                        ObjectOutputStream output = new ObjectOutputStream(byteOutput);
                        output.writeObject(now);
                        client.socketChannel.write(ByteBuffer.wrap(byteOutput.toByteArray())); // send command to Server

                        ByteBuffer inputBuffer = ByteBuffer.allocate(4096);
                        client.socketChannel.read(inputBuffer);
                        ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(inputBuffer.array()));
                        ServerResult serverResult = (ServerResult) input.readObject();

                        System.out.println(serverResult.result);
                    }

                }
            } catch (Exception e) {
                System.out.println("Соединение с сервером потеряно. Попробуем еще раз через некоторое время.");
                client.connect(PORT);
            }
            socketChannel.close();
        }
    }
}

