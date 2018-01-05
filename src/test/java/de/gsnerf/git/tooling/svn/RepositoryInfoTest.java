package de.gsnerf.git.tooling.svn;

import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author gsnerf
 */
public class RepositoryInfoTest {

    private final String EXPECTED_PATH = ".";
    private final String EXPECTED_URL = "http://some.where.url/with/some/path";
    private final String EXPECTED_REPOSITORY_ROOT = "http://some.where.url";
    private final String EXPECTED_REPOSITORY_UUID = "SOMEUUID";
    private final String EXPECTED_REVISION = "12345";
    private final String EXPECTED_NODE_KIND = "directory";
    private final String EXPECTED_SCHEDULE = "normal";
    private final String EXPECTED_LAST_AUTHOR = "some author";
    private final String EXPECTED_LAST_REVISION = "54321";
    private final String EXPECTED_LAST_DATE = "2018-01-05 19:49:09 +0100 (Fri, 05 Jan 2018)";

    @Test
    public void testGetPath_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getPath();

        // assert
        assertThat( result, is( equalTo( EXPECTED_PATH ) ) );
    }

    @Test
    public void testGetUrl_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getUrl();

        // assert
        assertThat( result, is( equalTo( EXPECTED_URL ) ) );
    }

    @Test
    public void testGetRepositoryRoot_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getRepositoryRoot();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_REPOSITORY_ROOT ) ) );
    }

    @Test
    public void testGetRepositoryUUID_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getRepositoryUUID();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_REPOSITORY_UUID ) ) );
    }

    @Test
    public void testGetRevision_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getRevision();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_REVISION ) ) );
    }

    @Test
    public void testGetNodeKind_initWithData_returnsExpectedValue() {
                // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getNodeKind();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_NODE_KIND ) ) );
    }

    @Test
    public void testGetSchedule_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getSchedule();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_SCHEDULE ) ) );
    }

    @Test
    public void testGetLastChangedAuthor_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getLastChangedAuthor();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_LAST_AUTHOR ) ) );
    }

    @Test
    public void testGetLastChangedRevision_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getLastChangedRevision();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_LAST_REVISION ) ) );
    }

    @Test
    public void testGetLastChangedDate_initWithData_returnsExpectedValue() {
        // arrange
        RepositoryInfo repositoryInfo = new RepositoryInfo( getPreparedParamData() );

        // act
        String result = repositoryInfo.getLastChangedDate();
        
        // assert
        assertThat( result, is( equalTo( EXPECTED_LAST_DATE ) ) );
    }

    private Map<String, String> getPreparedParamData() {
        Map<String, String> params = new HashMap<>();
        params.put( RepositoryInfo.INDICATOR_PATH, EXPECTED_PATH );
        params.put( RepositoryInfo.INDICATOR_URL, EXPECTED_URL );
        params.put( RepositoryInfo.INDICATOR_REPOSITORY_ROOT, EXPECTED_REPOSITORY_ROOT );
        params.put( RepositoryInfo.INDICATOR_REPOSITORY_UUID, EXPECTED_REPOSITORY_UUID );
        params.put( RepositoryInfo.INDICATOR_REVISION, EXPECTED_REVISION );
        params.put( RepositoryInfo.INDICATOR_NODE_KIND, EXPECTED_NODE_KIND );
        params.put( RepositoryInfo.INDICATOR_SCHEDULE, EXPECTED_SCHEDULE );
        params.put( RepositoryInfo.INDICATOR_LASTCHANGED_AUTHOR, EXPECTED_LAST_AUTHOR );
        params.put( RepositoryInfo.INDICATOR_LASTCHANGED_REVISION, EXPECTED_LAST_REVISION );
        params.put( RepositoryInfo.INDICATOR_LASTCHANGED_DATE, EXPECTED_LAST_DATE );
        return params;
    }

}
