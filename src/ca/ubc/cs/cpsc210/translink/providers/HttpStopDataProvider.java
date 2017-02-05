package ca.ubc.cs.cpsc210.translink.providers;

import ca.ubc.cs.cpsc210.translink.BusesAreUs;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for Translink Arrival Data Provider
 */
public class HttpStopDataProvider extends AbstractHttpDataProvider {
    private LatLon locn;

    public HttpStopDataProvider(LatLon locn) {
        super();
        this.locn = locn;
    }

    @Override
    /**
     * Produces URL used to query Translink web service for stops near
     * the location specified in call to constructor.
     *
     * @returns URL to query Translink web service for stop data
     *
     * It should look like:  http://api.translink.ca/rttiapi/v1/stops?apikey=[APIKey]&lat=[lat]&long=[long]&radius=2000"
     */
    protected URL getURL() throws MalformedURLException {
        String request;
        String lats = String.format("%.6f", locn.getLatitude());
        String longs = String.format("%.6f", locn.getLongitude());

        request = "http://api.translink.ca/rttiapi/v1/stops?apikey=" + BusesAreUs.TRANSLINK_API_KEY
                + "&lat=" + lats + "&long=" + longs + "&radius=2000";
        System.out.println(request);
        return new URL(request);
    }

    @Override
    public byte[] dataSourceToBytes() throws IOException {
        return new byte[0];
    }
}
