package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.parsers.exception.StopDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * A parser for the data returned by Translink stops query
 */
public class StopParser {
    /*
    "Name": "WB DAVIE ST FS BIDWELL ST",
    "AtStreet": "BIDWELL ST",
    "WheelchairAccess": 1,
    "BayNo": "N",
    "Latitude": 49.28646,
    "Longitude": -123.14043,
    "StopNo": 50001,
    "OnStreet": "DAVIE ST",
    "Routes": "C23",
    "City": "VANCOUVER"
    */

    private String filename;
    private String name;
    private int number;
    private Double Lat;
    private Double Lon;
    private String routes;


    public StopParser(String filename) {
        this.filename = filename;
    }

    /**
     * Parse stop data from the file and add all stops to stop manager.
     */
    public void parse() throws IOException, StopDataMissingException, JSONException {
        DataProvider dataProvider = new FileDataProvider(filename);

        parseStops(dataProvider.dataSourceToString());
    }

    /**
     * Parse stop information from JSON response produced by Translink.
     * Stores all stops and routes found in the StopManager and RouteManager.
     *
     * @param jsonResponse string encoding JSON data to be parsed
     * @throws JSONException            when JSON data does not have expected format
     * @throws StopDataMissingException when
     * <ul>
     * <li> JSON data is not an array </li>
     * <li> JSON data is missing Name, StopNo, Routes or location (Latitude or Longitude) elements for any stop</li>
     * </ul>
     */

    public void parseStops(String jsonResponse) throws JSONException, StopDataMissingException {

        JSONArray arrivals = new JSONArray(jsonResponse);

        for (int index = 0; index < arrivals.length(); index++) {
            JSONObject stop = arrivals.getJSONObject(index);
            parseStop(stop);
        }

        //
    }

    private void parseStop(JSONObject stop) throws JSONException, StopDataMissingException {
        if (stop.has("Name") && stop.has("StopNo") && stop.has("Latitude") && stop.has("Longitude") && stop.has("Routes")) {
            name = stop.getString("Name");
            number = stop.getInt("StopNo");
            Lat = stop.getDouble("Latitude");
            Lon = stop.getDouble("Longitude");
            routes = stop.getString("Routes");

            String[] route = routes.split(", ");
            Stop s = StopManager.getInstance().getStopWithId(number, name, new LatLon(Lat, Lon));
            for (String str : route) {
                Route r = RouteManager.getInstance().getRouteWithNumber(str);
                r.addStop(s);
                s.addRoute(r);
            }
        } else {
            throw new StopDataMissingException();
        }


    }
}
