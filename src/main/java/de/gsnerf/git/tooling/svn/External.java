package de.gsnerf.git.tooling.svn;

/**
 * This class represents all data that is part of an external as used by svn.
 * 
 * @author gsnerf
 */
public class External {
    
    private final String localPath;
    private final String remoteUrl;

    public External( String localPath, String remoteUrl ) {
        this.localPath = localPath;
        this.remoteUrl = remoteUrl;
    }

    /**
     * @return {@link String} denoting the relative path to the local checkout of the external
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * @return URL {@link String} denoting the remote location to check out
     */
    public String getRemoteUrl() {
        return remoteUrl;
    }
    
}
