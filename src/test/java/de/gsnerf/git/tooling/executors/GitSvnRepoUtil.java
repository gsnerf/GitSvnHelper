package de.gsnerf.git.tooling.executors;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Small utility to handle Git-SVN repositories for testing purposes.
 * @author gsnerf
 */
public class GitSvnRepoUtil {
    
    private GitSvnRepoUtil() {
        // utility classes should not be instantiated
    }

    public static String checkoutRepoWithGitSvn( File target ) throws Exception {
        GitSvnCommand command = new GitSvnCommand( target );
        File repoFile = getSvnRepositoryDirectory();
        String fullUri = getGitCompatibleUri( repoFile );
        command.execute( "clone", "-s", fullUri, target.getAbsolutePath() );
        return fullUri;
    }

    private static File getSvnRepositoryDirectory() throws Exception {
        URL repositoryUrl = GitSvnRepoUtil.class.getResource( "/SVN-Repository" );
        URI repositoryUri = repositoryUrl.toURI();
        return new File( repositoryUri );
    }

    private static String getGitCompatibleUri( File repoFile ) {
        URI repoUri = repoFile.toURI();
        String uriPath = repoUri.getPath();
        if (uriPath.matches( "^/[a-zA-Z]:.*" )) {
            String driveLetter = uriPath.substring( 1, 2 );
            String toReplace = "/" + driveLetter + ":";
            String replaceWith = "/" + driveLetter;
            uriPath = uriPath.replace( toReplace, replaceWith );
        }
        if (uriPath.endsWith( "/" )) {
            uriPath = uriPath.substring( 0, uriPath.length() - 1 );
        }
        return "file://" + uriPath;
    }
}
