package de.gsnerf.git.tooling.executors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This provides the possibility to access git-svn with arbitrary commands.
 * It is not checked if these commands are actually valid!
 * 
 * @author gsnerf
 */
public class GitSvnCommand {

    private final String gitCommand = "git";
    private final String svnPlugin = "svn";

    public GitSvnCommand() {
        // nothing to do for now
    }

    /**
     * Execute git-svn with the given parameters
     * @param params list of parameters to be passed to git-svn
     * @return the {@link CommandResult} containing the processes outputs
     * @throws IOException if anything happened while running the process
     */
    public CommandResult execute( String... params ) throws IOException {
        List<String> command = new ArrayList<>();
        command.add( gitCommand );
        command.add( svnPlugin );
        command.addAll( Arrays.asList( params ) );
        return Command.execute( command );
    }
}
