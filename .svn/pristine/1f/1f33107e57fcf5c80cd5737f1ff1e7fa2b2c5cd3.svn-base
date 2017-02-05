package ca.ubc.cs.cpsc210.translink.parsers;

import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RouteManager;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.providers.DataProvider;
import ca.ubc.cs.cpsc210.translink.providers.FileDataProvider;
import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for routes stored in a compact format in a txt file
 */
public class RouteMapParser {
    private String fileName;

    public RouteMapParser(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Parse the route map txt file
     */
    public void parse() {
        DataProvider dataProvider = new FileDataProvider(fileName);
        try {
            String c = dataProvider.dataSourceToString();
            if (!c.equals("")) {
                int posn = 0;
                while (posn < c.length()) {
                    int endposn = c.indexOf('\n', posn);
                    String line = c.substring(posn, endposn);
                    parseOnePattern(line);
                    posn = endposn + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse one route pattern, adding it to the route that is named within it
     *
     * @param str
     */
    private void parseOnePattern(String str) {

        String[] strings = str.split(";");
        if (strings[0].startsWith("N")) {
            String[] stringsWithRouteNumAndPattern = strings[0].split("-");
            if (stringsWithRouteNumAndPattern.length > 2) {
                String[] temp = new String[2];
                temp[0] = stringsWithRouteNumAndPattern[0];
                temp[1] = stringsWithRouteNumAndPattern[1] + "-" + stringsWithRouteNumAndPattern[2];
                stringsWithRouteNumAndPattern = temp;
            }
            if (stringsWithRouteNumAndPattern.length == 2) {
                String routeNumber = stringsWithRouteNumAndPattern[0].substring(1);
                String patternName = stringsWithRouteNumAndPattern[1];
                List<LatLon> locnstr = new ArrayList<LatLon>();
                for (int i = 1; i <= strings.length - 2; i = i + 2) {
                    LatLon locn = new LatLon(Double.parseDouble(strings[i]), Double.parseDouble(strings[i + 1]));
                    locnstr.add(locn);
                }
                if (!routeNumber.equals(null) && !patternName.equals(null) && !routeNumber.equals(""))
                    storeRouteMap(routeNumber, patternName, locnstr);
            }
        }
    }

    //


    /**
     * Store the parsed pattern into the named route
     * Your parser should call this method to insert each route pattern into the corresponding route object
     * There should be no need to change this method
     *
     * @param routeNumber the number of the route
     * @param patternName the name of the pattern
     * @param elements    the coordinate list of the pattern
     */
    private void storeRouteMap(String routeNumber, String patternName, List<LatLon> elements) {
        Route r = RouteManager.getInstance().getRouteWithNumber(routeNumber);
        RoutePattern rp = r.getPattern(patternName);
        rp.setPath(elements);
    }
}
