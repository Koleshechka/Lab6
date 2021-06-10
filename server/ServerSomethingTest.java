package server;

import commands.Command;
import tools.Console;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class ServerSomethingTest extends Thread {

    public Console serverConsole;
    private final SocketChannel socketChannel;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    ByteBuffer inputBuffer;
    ByteArrayOutputStream byteOutput;

    public ServerSomethingTest(SocketChannel socketChannel, Console console) {
        this.serverConsole = console;
        this.socketChannel = socketChannel;
        inputBuffer = ByteBuffer.allocate(4096);
        byteOutput = new ByteArrayOutputStream(4096);
        System.out.println("new client copy server!!!");
        start();
    }

    public static void clear(Buffer buffer)
    {
        buffer.clear();
    }

    @Override
    public void run() {

        try {
            socketChannel.configureBlocking(false);
            while (true) {
                ((Buffer)inputBuffer).clear();
                int count = socketChannel.read(inputBuffer);
                if (count <= 0) continue;
                inputStream = new ObjectInputStream(new ByteArrayInputStream(inputBuffer.array()));
                Command command = (Command) inputStream.readObject();

                byteOutput = new ByteArrayOutputStream(4096);
                outputStream = new ObjectOutputStream(byteOutput);
                outputStream.writeObject(new ServerResult(command.doit(serverConsole)));
                socketChannel.write(ByteBuffer.wrap(byteOutput.toByteArray()));
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" ");
        }
    }
}