package de.gsnerf.git.tooling.svn;

import de.gsnerf.git.tooling.svn.External;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExternalsParser {


    private final List<External> externals = new ArrayList<>();

    /**
     * C'tor parses the output into a list of externals without known SVN-Root prefix (expects only links to external repositories).
     * @param externalOutput  list of external entries as can be seen when calling "svn propget svn:externals"
     */
    public ExternalsParser( List<String> externalOutput ) {
        this( externalOutput, "" );
    }

    /**
     * C'tor parses the output into a list of externals without known SVN-Root prefix (expects only links to external repositories).
     * @param externalOutput  list of external entries as can be seen when calling "svn propget svn:externals"
     * @param ownSvnRoot the path showing to the svn remote repository represented by the local git-svn repository
     */
    public ExternalsParser( List<String> externalOutput, String ownSvnRoot ) {
        for ( String externalLine : externalOutput ) {
            External external = extractExternal(externalLine, ownSvnRoot);
            externals.add(external);
        }
    }

    /**
     * Format of an external line is "ExternalUrl targetPath".
     * If the ExternalPath starts with a '^' it references to a path inside the same svn repository, starting at it's root.
     * 
     * @param externalLine The single external line to parse (following the format described above).
     * @param ownSvnRoot The root-URL of the current SVN, to be used when the external points into the same repository.
     * @return The {@link External} Object representing the given external line
     */
    private External extractExternal( String externalLine, String ownSvnRoot ) {
        String[] splitLine = externalLine.split( " ", 2 );
        String localPath = splitLine[1];
        String sourceUrl = splitLine[0];
        if (!ownSvnRoot.isEmpty() && sourceUrl.startsWith( "^" )) {
            sourceUrl = sourceUrl.replace( "^", ownSvnRoot );
        }
        External external = new External( localPath, sourceUrl );
        return external;
    }

    /**
     * This updates all externals pointing to the same SVN repository as the local repository, with the given svnroot.
     * Attention: This only updates entries that doesn't already have been reworked to point to an SVN-Repositry.
     * @param svnRoot the root url to use as the starting point for the "internal" externals
     */
    public void updateWithSvnRoot( String svnRoot ) {
        List<External> newExternals = new ArrayList<>();
        for ( External external : externals ) {
            if (external.getRemoteUrl().startsWith( "^" )) {
                String url = external.getRemoteUrl().replace( "^", svnRoot );
                newExternals.add( new External( external.getLocalPath(), url ) );
            } else {
                newExternals.add( external );
            }
        }

        externals.clear();
        externals.addAll( newExternals );
    }

    /**
     * @return An unmodifiable {@link List} of the {@link External}s.
     */
    public List<External> getExternals() {
        return Collections.unmodifiableList( externals );
    }

    /**
     * This creates a readable string representation to be used when printing the content to the console or something like that.
     * We especially don't want to mess with the regular {@code toString()} method!
     * @return printable string
     */
    public String toReadableString() {
        StringBuilder sb = new StringBuilder().append( "[" ).append( System.lineSeparator() );
        for ( External external : externals ) {
            sb.append( "    Name: " ).append(external.getLocalPath() ).append( "; URL: " ).append(external.getRemoteUrl() ).append( System.lineSeparator() );
        }
        sb.append( "]" ).append( System.lineSeparator() );
        return sb.toString();
    }


}
