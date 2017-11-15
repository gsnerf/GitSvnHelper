package de.gsnerf.git.tooling.executors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This provides the possibility to access a svn client with arbitrary commands.
 * It is not checked if these commands are actually valid!
 * 
 * @author gsnerf
 */
public class SvnCommand {

    private final String svnClient;

    /**
     * C'tor which causes the default SVN client registered in the system to be used.
     */
    public SvnCommand() {
        this( "svn" );
    }

    /**
     * C'tor which causes the use of the given custom client. The given client string should meet one of the following conditions:
     * <ul>
     *   <li>The client is defined in PATH and can be called directly</li>
     *   <li>The client string is an absolute path to the binary to be called</li>
     * </ul>
     * @param customClient the name of the client or an absolute path to the binary
     */
    public SvnCommand( String customClient ) {
        svnClient = customClient;
    }

    /**
     * Execute the defined svn client with the given parameters
     * @param params list of parameters to be passed to the client
     * @return the {@link CommandResult} containing the processes outputs
     * @throws IOException if anything happened while running the process
     */
    public CommandResult execute( String... params ) throws IOException {
        List<String> command = new ArrayList<>();
        command.add( svnClient );
        command.addAll( Arrays.asList( params ) );
        return Command.execute( command );
    }
}
