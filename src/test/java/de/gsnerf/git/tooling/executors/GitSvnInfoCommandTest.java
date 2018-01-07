package de.gsnerf.git.tooling.executors;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URI;
import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import de.gsnerf.git.tooling.svn.RepositoryInfo;

/**
 *
 * @author gsnerf
 */
public class GitSvnInfoCommandTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    
    @Test
    public void testGetRepositoryInfo() throws Exception {
        String fullUri = checkoutRepoWithGitSvn();
        GitSvnInfoCommand instance = new GitSvnInfoCommand(folder.getRoot());
        
        // act
        RepositoryInfo result = instance.getRepositoryInfo();
        
        // assert
        assertThat( result.getRepositoryRoot(), Matchers.is( fullUri ));
    }

    private String checkoutRepoWithGitSvn() throws Exception {
        GitSvnCommand command = new GitSvnCommand(folder.getRoot());
        File repoFile = getSvnRepositoryDirectory();
        String fullUri = getGitCompatibleUri(repoFile);
        command.execute( "clone", "-s", fullUri, folder.getRoot().getAbsolutePath());
        return fullUri;
    }
    private File getSvnRepositoryDirectory() throws Exception {
        URL repositoryUrl = getClass().getResource( "/SVN-Repository" );
        URI repositoryUri = repositoryUrl.toURI();
        return new File(repositoryUri);
    }

    private String getGitCompatibleUri( File repoFile ) {
        URI repoUri = repoFile.toURI();
        String uriPath = repoUri.getPath();
        if (uriPath.matches( "^/[a-zA-Z]:.*")) {
            String driveLetter = uriPath.substring( 1, 2);
            String toReplace = "/" + driveLetter + ":";
            String replaceWith = "/" + driveLetter;
            uriPath = uriPath.replace( toReplace, replaceWith);
        }
        if (uriPath.endsWith( "/")) {
            uriPath = uriPath.substring( 0, uriPath.length() - 1);
        }
        return "file://" + uriPath;
    }
    
}
