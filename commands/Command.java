package commands;

import tools.Console;

import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    public abstract String doit(Console console) throws IOException;
}
