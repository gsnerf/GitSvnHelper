package de.gsnerf.git.tooling.svn;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;

public class ExternalsParserTest {
    
    private static final String SVN_ROOT = "SOME/ROOT/";
    private static final String SVN_OTHER_ROOT = "SOME/OTHER/ROOT/";
    
    @Test
    public void testUpdateWithSvnRoot_passOnlyExternalsToCtor_getExternalsReturnsAdaptedUrls() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs);
        
        // act
        parser.updateWithSvnRoot( SVN_ROOT );
        
        // assert
        List<External> externals = parser.getExternals();
        String resultUrl1 = externals.get(0).getRemoteUrl();
        String resultUrl2 = externals.get(1).getRemoteUrl();
        assertThat(resultUrl1, startsWith( SVN_ROOT ));
        assertThat(resultUrl2, startsWith( SVN_ROOT ));
    }
    
    @Test
    public void testUpdateWithSvnRoot_passExternalsAndSvnRootToCtor_getExternalsReturnsOriginalUrls() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs, SVN_ROOT);
        
        // act
        parser.updateWithSvnRoot( SVN_OTHER_ROOT );
        
        // assert
        List<External> externals = parser.getExternals();
        String resultUrl1 = externals.get(0).getRemoteUrl();
        String resultUrl2 = externals.get(1).getRemoteUrl();
        assertThat(resultUrl1, startsWith( SVN_ROOT ));
        assertThat(resultUrl2, startsWith( SVN_ROOT ));
    }

    @Test
    public void testGetExternals_passOnlyExternalsToCtor_returnsExpectedNumberOfExternalObjects() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs);
        int expected = inputs.size();
        
        // act
        List<External> externals = parser.getExternals();
        
        // assert
        int result = externals.size();
        assertThat(result, is( equalTo( expected)));
    }
    
    @Test
    public void testGetExternals_passOnlyExternalsToCtor_returnedExternalObjectsHaveUntouchedUrl() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs);
        
        // act
        List<External> externals = parser.getExternals();
        
        // assert
        String resultUrl1 = externals.get(0).getRemoteUrl();
        String resultUrl2 = externals.get(1).getRemoteUrl();
        assertThat(resultUrl1, startsWith( "^" ));
        assertThat(resultUrl2, startsWith( "^" ));
    }

    @Test
    public void testGetExternals_passExternalsAndSvnRootToCtor_returnsExpectedNumberOfExternalObjects() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs, SVN_ROOT);
        int expected = inputs.size();
        
        // act
        List<External> externals = parser.getExternals();
        
        // assert
        int result = externals.size();
        assertThat(result, is( equalTo( expected)));
    }
    
    @Test
    public void testGetExternals_passExternalsAndSvnRootToCtor_returnedExternalObjectsHaveAdaptedUrl() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs, SVN_ROOT);
        
        // act
        List<External> externals = parser.getExternals();
        
        // assert
        String resultUrl1 = externals.get(0).getRemoteUrl();
        String resultUrl2 = externals.get(1).getRemoteUrl();
        assertThat(resultUrl1, startsWith( SVN_ROOT ));
        assertThat(resultUrl2, startsWith( SVN_ROOT ));
    }

    @Test
    public void testToReadableString() {
        // arrange
        ArrayList<String> inputs = createExternalLines();
        ExternalsParser parser = new ExternalsParser(inputs, SVN_ROOT);
        
        // act
        String result = parser.toReadableString();
        
        // assert
        assertThat( result, not( isEmptyOrNullString() ) );
        
    }
    
    // helper methods --------------------------------------------------------------------------------------------------

    private ArrayList<String> createExternalLines() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("^/trunk/module_B other_module_1");
        inputs.add("^/trunk/module_C other_module_2");
        return inputs;
    }
}
