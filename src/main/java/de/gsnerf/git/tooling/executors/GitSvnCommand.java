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

    public CommandResult execute( String... params ) throws IOException {
        List<String> command = new ArrayList<>();
        command.add( gitCommand );
        command.add( svnPlugin );
        command.addAll( Arrays.asList( params ) );
        return Command.execute( command );
    }
}
