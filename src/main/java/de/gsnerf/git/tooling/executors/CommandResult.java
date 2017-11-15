package de.gsnerf.git.tooling.executors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gsnerf
 */
public class CommandResult {

    private final List<String> output = new ArrayList<>();
    private final List<String> error = new ArrayList<>();

    /**
     * Default C'tor
     * @param process the {@link Process} that was/is executed
     * @throws IOException 
     */
    public CommandResult( Process process ) throws IOException {
        readStreamToList( process.getInputStream(), output );
        readStreamToList( process.getErrorStream(), error );
    }

    private void readStreamToList( InputStream inputStream, List<String> resultList ) throws IOException {
        try (
                InputStream is = inputStream;
                InputStreamReader isr = new InputStreamReader( is );
                BufferedReader br = new BufferedReader( isr ); //
                ) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    resultList.add( line );
                }
            }
        }
    }

    /**
     * The processes output written to std-out
     * @return list of all lines written by the process
     */
    public List<String> getOutput() {
        return Collections.unmodifiableList( output );
    }

    /**
     * The processes output written to std-err
     * @return list of all lines written by the process
     */
    public List<String> getError() {
        return Collections.unmodifiableList( error );
    }

}
