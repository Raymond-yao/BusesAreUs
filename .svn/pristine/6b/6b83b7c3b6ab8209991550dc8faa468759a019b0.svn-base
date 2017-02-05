package ca.ubc.cs.cpsc210.translink.providers;

import ca.ubc.cs.cpsc210.translink.BusesAreUs;
import ca.ubc.cs.cpsc210.translink.model.Stop;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for Translink Arrival Data Provider
 */
public class HttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Stop stop;

    public HttpArrivalDataProvider(Stop stop) {
        super();
        this.stop = stop;
    }

    @Override
    /**
     * Produces URL used to query Translink web service for expected arrivals at
     * the stop specified in call to constructor.
     *
     * @returns URL to query Translink web service for arrival data
     *
     *
     */
    protected URL getURL() throws MalformedURLException {
        return new URL("http://api.translink.ca/rttiapi/v1/stops/" + String.valueOf(stop.getNumber()) +"/estimates?apikey=" + BusesAreUs.TRANSLINK_API_KEY );
    }

    @Override
    public byte[] dataSourceToBytes() throws IOException {
        return new byte[0];
    }
}
