package de.gsnerf.git.tooling.executors;

import de.gsnerf.git.tooling.svn.RepositoryInfo;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gsnerf
 */
public class GitSvnInfoCommand extends GitSvnCommand {

    public GitSvnInfoCommand() {
        super();
    }
    
    public GitSvnInfoCommand(File workingDirectory) {
        super(workingDirectory);
    }
    

    public RepositoryInfo getRepositoryInfo() {
        Map<String, String> infoOutput;
        try {
            CommandResult result = super.execute( "info" );

            infoOutput = extractMapFromOutput( result.getOutput() );
        } catch (IOException ioe) {
            // TODO: somehow adequately log the error!
            infoOutput = new HashMap<>();
        }
        return new RepositoryInfo( infoOutput );
    }
    
    /**
     * This parses the "svn info" output into a searchable map.
     * @param mapTypeCommandOutput list of output lines from executing "svn info" or "git svn info"
     * @return searchable {@link Map}
     */
    private Map<String, String> extractMapFromOutput( List<String> mapTypeCommandOutput ) {
        Map<String, String> outputMap = new HashMap<>();
        for ( String line : mapTypeCommandOutput ) {
            String[] infoEntry = line.split( ":", 2 );
            outputMap.put( infoEntry[0].trim(), infoEntry[1].trim() );
        }
        return outputMap;
    }
    
    
    
}
