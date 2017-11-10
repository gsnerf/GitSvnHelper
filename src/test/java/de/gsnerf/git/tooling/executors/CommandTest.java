package de.gsnerf.git.tooling.executors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author gsnerf
 */
public class CommandTest {

    public CommandTest() {
    }

    @Test
    public void testExecute_callCommandThatWritesToErrorStream_getErrorOnResultIsNotEmpty() throws Exception {
        // arrange
        List<String> command = Arrays.asList( "java", "-version" );

        // act
        CommandResult commandResult = Command.execute( command );
        List<String> result = commandResult.getError();

        // assert
        assertThat( result.size(), is( not( 0 ) ) );
    }

    @Test
    public void testExecute_callCommandThatWritesToErrorStream_getErrorOnResultReturnsExpectedOutput() throws Exception {
        // arrange
        List<String> command = Arrays.asList( "java", "-version" );
        String expected = "java version";

        // act
        CommandResult commandResult = Command.execute( command );
        String result = commandResult.getError().get( 0 );

        // assert
        assertThat( result, startsWith( expected ) );
    }
}
