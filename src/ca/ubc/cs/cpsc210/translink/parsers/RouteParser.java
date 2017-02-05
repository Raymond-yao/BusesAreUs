package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.parsers.exception.RouteDataMissingException;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Parse route information in JSON format.
 */
public class RouteParser {
    private String filename;
    private String destination;
    private String direction;
    private String patternNo;
    private String name;
    private String number;


    public RouteParser(String filename) {
        this.filename = filename;
    }

    /**
     * Parse route data from the file and add all route to the route manager.
     */
    public void parse() throws IOException, RouteDataMissingException, JSONException {
        DataProvider dataProvider = new FileDataProvider(filename);

        parseRoutes(dataProvider.dataSourceToString());
    }

    /**
     * Parse route information from JSON response produced by Translink.
     * Stores all routes and route patterns found in the RouteManager.
     *
     * @param jsonResponse string encoding JSON data to be parsed
     * @throws JSONException             when JSON data does not have expected format
     * @throws RouteDataMissingException when
     *<ul>
     *<li> JSON data is not an array </li>
     *<li> JSON data is missing Name, StopNo, Routes or location elements for any stop</li>
     *</ul>
     */

    public void parseRoutes(String jsonResponse) throws JSONException, RouteDataMissingException {
        JSONArray arrivals = new JSONArray(jsonResponse);

        for (int index = 0; index < arrivals.length(); index++) {
            JSONObject route = arrivals.getJSONObject(index);
            parseRoute(route);
        }
        //
    }

    private void parseRoute(JSONObject route) throws JSONException, RouteDataMissingException {
        if (route.has("Name") && route.has("RouteNo") && route.has("Patterns")) {
            name = route.getString("Name");
            number = route.getString("RouteNo");
            JSONArray routePatterns = route.getJSONArray("Patterns");
            parsePatterns(routePatterns);
        } else {
            throw new RouteDataMissingException();
        }
    }


    private void parsePatterns(JSONArray routePatterns) throws JSONException, RouteDataMissingException {
        for (int index = 0; index < routePatterns.length(); index++) {
            JSONObject pattern = routePatterns.getJSONObject(index);
            parsePattern(pattern);
            storeRouteManager();
        }
    }


    private void parsePattern(JSONObject pattern) throws JSONException, RouteDataMissingException {
        if (pattern.has("Destination") && pattern.has("Direction") && pattern.has("PatternNo")) {
            destination = pattern.getString("Destination");
            direction = pattern.getString("Direction");
            patternNo = pattern.getString("PatternNo");
        } else {
            throw new RouteDataMissingException();
        }
    }

    private void storeRouteManager() {
        Route r = RouteManager.getInstance().getRouteWithNumber(number, name);
        RoutePattern rp = r.getPattern(patternNo, destination, direction);
    }
}
