package de.gsnerf.git.tooling.executors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SvnCommand {

    private final String svnClient;

    public SvnCommand() {
        this( "svn" );
    }

    public SvnCommand( String customClient ) {
        svnClient = customClient;
    }

    public CommandResult execute( String... params ) throws IOException {
        List<String> command = new ArrayList<>();
        command.add( svnClient );
        command.addAll( Arrays.asList( params ) );
        return Command.execute( command );
    }
}
