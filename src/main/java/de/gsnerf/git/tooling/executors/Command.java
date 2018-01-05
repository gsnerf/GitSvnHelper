package de.gsnerf.git.tooling.executors;

import java.io.File;
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
     * Execution happens in the current directory.
     * @param command {@link List} representing the command to execute. Use a new entry for each parameter and the command itself.
     * @return A {@link List} of Strings containing all unprocessed lines created as output by the command.
     * @throws IOException when some problem occured while reading the output
     */
    public static CommandResult execute( List<String> command ) throws IOException {
        return execute(new File("."), command);
    }
    
    /**
     * @param workingDirectory the {@link File} pointing to the directory the command should be executed in
     * @param command {@link List} representing the command to execute. Use a new entry for each parameter and the command itself.
     * @return A {@link List} of Strings containing all unprocessed lines created as output by the command.
     * @throws IOException when some problem occured while reading the output
     */
    public static CommandResult execute( File workingDirectory, List<String> command ) throws IOException {
        ProcessBuilder builder = new ProcessBuilder( command );
        builder.directory( workingDirectory );
        Process process = builder.start();
        return new CommandResult(process);
    }

}