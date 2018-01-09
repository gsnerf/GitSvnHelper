package de.gsnerf.git.tooling.svn;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author gsnerf
 */
public class ExternalCollectionTest {
    
    private static final String SVN_ROOT = "SOME/ROOT/";
    private static final String SVN_OTHER_ROOT = "SOME/OTHER/ROOT/";
    

    @Test
    public void testUpdateWithSvnRoot_passRelativeExternalsToCtor_getExternalsReturnsAdaptedUrls() {
        // arrange
        List<External> inputs = createExternalLinesWithRelativeUrl();
        ExternalCollection collection = new ExternalCollection( inputs );

        // act
        collection.updateWithSvnRoot( SVN_ROOT );

        // assert
        List<External> externals = collection.getExternals();
        String resultUrl1 = externals.get( 0 ).getRemoteUrl();
        String resultUrl2 = externals.get( 1 ).getRemoteUrl();
        assertThat( resultUrl1, startsWith( SVN_ROOT ) );
        assertThat( resultUrl2, startsWith( SVN_ROOT ) );
    }

    @Test
    public void testUpdateWithSvnRoot_passAbsoluteExternalsToCtor_getExternalsReturnsOriginalUrls() {
        // arrange
        ArrayList<External> inputs = createExternalLinesWithAbsoluteUrl();
        ExternalCollection collection = new ExternalCollection( inputs );

        // act
        collection.updateWithSvnRoot( SVN_OTHER_ROOT );

        // assert
        List<External> externals = collection.getExternals();
        String resultUrl1 = externals.get( 0 ).getRemoteUrl();
        String resultUrl2 = externals.get( 1 ).getRemoteUrl();
        assertThat( resultUrl1, startsWith( SVN_ROOT ) );
        assertThat( resultUrl2, startsWith( SVN_ROOT ) );
    }

    @Test
    public void testToReadableString() {
        // arrange
        ArrayList<External> inputs = createExternalLinesWithAbsoluteUrl();
        ExternalCollection collection = new ExternalCollection( inputs );

        // act
        String result = collection.toReadableString();

        // assert
        assertThat( result, is( not( emptyOrNullString() ) ) );

    }

    // helper methods --------------------------------------------------------------------------------------------------
    private ArrayList<External> createExternalLinesWithRelativeUrl() {
        ArrayList<External> inputs = new ArrayList<>();
        inputs.add( new External( "other_module_1", "^/trunk/module_B" ) );
        inputs.add( new External( "other_module_2", "^/trunk/module_C" ) );
        return inputs;
    }
    
    private ArrayList<External> createExternalLinesWithAbsoluteUrl() {
        ArrayList<External> inputs = new ArrayList<>();
        inputs.add( new External( "other_module_1", SVN_ROOT + "trunk/module_B" ) );
        inputs.add( new External( "other_module_2", SVN_ROOT + "trunk/module_C" ) );
        return inputs;
    }
}
