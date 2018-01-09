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
        String fullUri = GitSvnRepoUtil.checkoutRepoWithGitSvn( folder.getRoot() );
        GitSvnInfoCommand instance = new GitSvnInfoCommand( folder.getRoot() );

        // act
        RepositoryInfo result = instance.getRepositoryInfo();

        // assert
        assertThat( result.getRepositoryRoot(), Matchers.is( fullUri ) );
    }

}
