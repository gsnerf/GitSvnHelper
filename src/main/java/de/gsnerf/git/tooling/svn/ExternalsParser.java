package de.gsnerf.git.tooling.svn;

import de.gsnerf.git.tooling.svn.External;
import java.util.ArrayList;
import java.util.List;

public class ExternalsParser {

    private final ExternalCollection collection;

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
        List<External> externals = new ArrayList<>();
        for ( String externalLine : externalOutput ) {
            External external = extractExternal(externalLine, ownSvnRoot);
            externals.add(external);
        }
        collection = new ExternalCollection(externals );
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

    public ExternalCollection getExternalCollection() {
        return collection;
    }


}
