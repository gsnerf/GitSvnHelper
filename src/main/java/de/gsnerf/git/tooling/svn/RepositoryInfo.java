package de.gsnerf.git.tooling.svn;

import java.util.Map;

/**
 * This represents the basic repository information available when calling "svn info" or "git svn info" on the
 * commandline.
 *
 * @author gsnerf
 */
public class RepositoryInfo {

    public static final String INDICATOR_PATH = "Path";
    public static final String INDICATOR_URL = "URL";
    public static final String INDICATOR_REPOSITORY_ROOT = "Repository Root";
    public static final String INDICATOR_REPOSITORY_UUID = "Repository UUID";
    public static final String INDICATOR_REVISION = "Revision";
    public static final String INDICATOR_NODE_KIND = "Node Kind";
    public static final String INDICATOR_SCHEDULE = "Schedule";
    public static final String INDICATOR_LASTCHANGED_AUTHOR = "Last Changed Author";
    public static final String INDICATOR_LASTCHANGED_REVISION = "Last Changed Rev";
    public static final String INDICATOR_LASTCHANGED_DATE = "Last Changed Date";

    private final String path;
    private final String url;
    private final String repositoryRoot;
    private final String repositoryUUId;
    private final String revision;
    private final String nodeKind;
    private final String schedule;
    private final String lastChangedAuthor;
    private final String lastChangedRevision;
    private final String lastChangedDate;

    /**
     * This C'tor builds the whole information system, by processing a {@link Map} containing all the "svn info" data.
     * The maps key ir the data identifier as can be found lefthand of ':' in the commandlin output of "svn info". The
     * content is the right hand of ':'.
     *
     * @param infoParams {@link Map} containing all "svn info" information as described above
     */
    public RepositoryInfo( Map<String, String> infoParams ) {
        path = retrieveParam( infoParams, INDICATOR_PATH );
        url = retrieveParam( infoParams, INDICATOR_URL );
        repositoryRoot = retrieveParam( infoParams, INDICATOR_REPOSITORY_ROOT );
        repositoryUUId = retrieveParam( infoParams, INDICATOR_REPOSITORY_UUID );
        revision = retrieveParam( infoParams, INDICATOR_REVISION );
        nodeKind = retrieveParam( infoParams, INDICATOR_NODE_KIND );
        schedule = retrieveParam( infoParams, INDICATOR_SCHEDULE );
        lastChangedAuthor = retrieveParam( infoParams, INDICATOR_LASTCHANGED_AUTHOR );
        lastChangedRevision = retrieveParam( infoParams, INDICATOR_LASTCHANGED_REVISION );
        lastChangedDate = retrieveParam( infoParams, INDICATOR_LASTCHANGED_DATE );
    }

    private String retrieveParam( Map<String, String> infoParams, String indicator ) {
        return infoParams.containsKey( indicator ) ? infoParams.get( indicator ) : "";
    }

    /**
     * @return the path of the local checkout (maybe something useless like ".")
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the remote URL of the checkout
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the remote URL of the SVN-Server (only the root, not the complete checkout path)
     */
    public String getRepositoryRoot() {
        return repositoryRoot;
    }

    public String getRepositoryUUID() {
        return repositoryUUId;
    }

    /**
     * @return the currently checked out revision number
     */
    public String getRevision() {
        return revision;
    }

    public String getNodeKind() {
        return nodeKind;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getLastChangedAuthor() {
        return lastChangedAuthor;
    }

    public String getLastChangedRevision() {
        return lastChangedRevision;
    }

    public String getLastChangedDate() {
        return lastChangedDate;
    }

    /**
     * This creates a printable string to be able to actually read the content.
     */
    public String toReadableString() {
        StringBuilder sb = new StringBuilder().append( "[" ).append( System.lineSeparator() );
        addEntry( sb, INDICATOR_PATH, path );
        addEntry( sb, INDICATOR_URL, url );
        addEntry( sb, INDICATOR_REPOSITORY_ROOT, repositoryRoot );
        addEntry( sb, INDICATOR_REPOSITORY_UUID, repositoryUUId );
        addEntry( sb, INDICATOR_REVISION, revision );
        addEntry( sb, INDICATOR_NODE_KIND, nodeKind );
        addEntry( sb, INDICATOR_SCHEDULE, schedule );
        addEntry( sb, INDICATOR_LASTCHANGED_AUTHOR, lastChangedAuthor );
        addEntry( sb, INDICATOR_LASTCHANGED_REVISION, lastChangedDate );
        addEntry( sb, INDICATOR_LASTCHANGED_DATE, lastChangedRevision );
        sb.append( "]" ).append( System.lineSeparator() );
        return sb.toString();
    }

    private void addEntry( StringBuilder sb, String indicator, String entry ) {
        sb.append( "    " ).append( indicator ).append( ": " ).append( entry ).append(
                System.lineSeparator() );
    }

}
