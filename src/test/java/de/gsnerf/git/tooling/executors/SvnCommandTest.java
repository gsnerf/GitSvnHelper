package de.gsnerf.git.tooling.executors;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author gsnerf
 */
public class SvnCommandTest {

    public SvnCommandTest() {
    }

    @Test
    public void testExecute_defaultInit_returnsOutput() throws Exception {
        // arrange
        String[] params = { "help" };
        SvnCommand command = new SvnCommand();

        // act
        List<String> result = command.execute( params ).getOutput();

        // assert
        assertThat( result, is( not( empty() ) ) );
    }

}
