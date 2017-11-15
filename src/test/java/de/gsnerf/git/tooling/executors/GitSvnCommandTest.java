package de.gsnerf.git.tooling.executors;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author gsnerf
 */
public class GitSvnCommandTest {

    public GitSvnCommandTest() {
    }

    @Test
    public void testExecute_callHelp_returnsOutput() throws Exception {
        // arrange
        String[] params = { "help" };
        GitSvnCommand command = new GitSvnCommand();

        // act
        List<String> result = command.execute( params ).getOutput();

        // assert
        assertThat( result, is( not( empty() ) ) );
    }

}
