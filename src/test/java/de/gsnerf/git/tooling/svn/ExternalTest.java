package de.gsnerf.git.tooling.svn;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class ExternalTest {

    private static final String SOME_LOCAL_PATH = "some\\local\\path";
    private static final String SOME_RANDOM_URL = "https://some.random.url/to/check";

    @Test
    public void testGetLocalPath_initializedThroughCtor_returnsEnteredValue() {
        // arrange
        External external = new External( SOME_LOCAL_PATH, SOME_RANDOM_URL );

        // act
        String result = external.getLocalPath();

        // assert
        assertThat( result, is( equalTo( SOME_LOCAL_PATH ) ) );
    }

    @Test
    public void testGetRemoteUrl_initializedThroughCtor_returnsEnteredValue() {
        // arrange
        External external = new External( SOME_LOCAL_PATH, SOME_RANDOM_URL );

        // act
        String result = external.getRemoteUrl();

        // assert
        assertThat( result, is( equalTo( SOME_RANDOM_URL ) ) );
    }

}
