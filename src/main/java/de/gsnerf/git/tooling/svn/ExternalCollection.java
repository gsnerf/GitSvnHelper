package de.gsnerf.git.tooling.svn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gsnerf
 */
public class ExternalCollection {
    
    private final List<External> externals;
    
    public ExternalCollection(List<External> externals) {
        this.externals = externals;
    }
    
    /**
     * @return An unmodifiable {@link List} of the {@link External}s.
     */
    public List<External> getExternals() {
        return Collections.unmodifiableList( externals );
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
