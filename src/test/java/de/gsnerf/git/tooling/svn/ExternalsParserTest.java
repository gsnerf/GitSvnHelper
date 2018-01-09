package de.gsnerf.git.tooling.svn;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ExternalsParserTest {

    private static final String SVN_ROOT = "SOME/ROOT/";

    @Test
    public void testGetExternalCollection_passOnlyExternalsToCtor_returnsCollectionWithExpectedNumberOfExternalObjects() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser( inputs );
        int expected = inputs.size();

        // act
        ExternalCollection collection = parser.getExternalCollection();

        // assert
        List<External> result = collection.getExternals();
        assertThat( result, hasSize( expected ) );
    }

    @Test
    public void testGetExternals_passOnlyExternalsToCtor_returnedExternalObjectsHaveUntouchedUrl() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser( inputs );

        // act
        ExternalCollection collection = parser.getExternalCollection();

        // assert
        List<External> externals = collection.getExternals();
        String resultUrl1 = externals.get( 0 ).getRemoteUrl();
        String resultUrl2 = externals.get( 1 ).getRemoteUrl();
        assertThat( resultUrl1, startsWith( "^" ) );
        assertThat( resultUrl2, startsWith( "^" ) );
    }

    @Test
    public void testGetExternals_passExternalsAndSvnRootToCtor_returnsExpectedNumberOfExternalObjects() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser( inputs, SVN_ROOT );
        int expected = inputs.size();

        // act
        ExternalCollection collection = parser.getExternalCollection();

        // assert
        List<External> result = collection.getExternals();
        assertThat( result, hasSize( expected ) );
    }

    @Test
    public void testGetExternals_passExternalsAndSvnRootToCtor_returnedExternalObjectsHaveAdaptedUrl() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser( inputs, SVN_ROOT );

        // act
        ExternalCollection collection = parser.getExternalCollection();

        // assert
        List<External> externals = collection.getExternals();
        String resultUrl1 = externals.get( 0 ).getRemoteUrl();
        String resultUrl2 = externals.get( 1 ).getRemoteUrl();
        assertThat( resultUrl1, startsWith( SVN_ROOT ) );
        assertThat( resultUrl2, startsWith( SVN_ROOT ) );
    }

    // helper methods --------------------------------------------------------------------------------------------------
    private ArrayList<String> createExternalLines() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add( "^/trunk/module_B other_module_1" );
        inputs.add( "^/trunk/module_C other_module_2" );
        return inputs;
    }
}
