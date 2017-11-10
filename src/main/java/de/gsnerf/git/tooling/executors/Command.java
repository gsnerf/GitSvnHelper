package de.gsnerf.git.tooling.executors;

import java.io.IOException;
import java.util.List;

/**
 * This utility class provides an easy to use way to execute a command and return it's output for later processing.
 * 
 * @author gsnerf
 */
public class Command {

    private Command() {
        // this utility class does not need to be instantiated
    }
    
    /**
     * @param command {@link List} representing the command to execute. Use a new entry for each parameter and the command itself.
     * @return A {@link List} of Strings containing all unprocessed lines created as output by the command.
     * @throws IOException when some problem occured while reading the output
     */
    public static CommandResult execute( List<String> command ) throws IOException {
        Process process = new ProcessBuilder( command ).start();
        return new CommandResult(process);
    }

}