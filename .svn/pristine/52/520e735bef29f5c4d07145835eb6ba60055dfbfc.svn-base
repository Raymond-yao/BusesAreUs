package ca.ubc.cs.cpsc210.translink.model;

import ca.ubc.cs.cpsc210.translink.util.LatLon;

import java.util.*;

/**
 * Represents a bus stop with an number, name, location (lat/lon)
 * set of routes which stop at this stop and a list of arrivals.
 */
//
public class Stop implements Iterable<Arrival> {
    private List<Arrival> arrivals;
    private LatLon locn;
    private Set<Route> routes;
    private String name;
    private int number;


    /**
     * Constructs a stop with given number, name and location.
     * Set of routes and list of arrivals are empty.
     *
     * @param number the number of this stop
     * @param name   name of this stop
     * @param locn   location of this stop
     */
    public Stop(int number, String name, LatLon locn) {
        this.number = number;
        this.name = name;
        this.locn = locn;
        routes = new HashSet<Route>();
        arrivals = new ArrayList<>();
    }

    /**
     * getter for name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for locn
     *
     * @return the location
     */
    public LatLon getLocn() {
        return locn;
    }

    /**
     * getter for number
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * getter for set of routes
     *
     * @return the set of routes using this stop
     */
    public Set<Route> getRoutes() {
        return Collections.unmodifiableSet(routes);
    }

    /**
     * getter for list of arrivals
     *
     * @return the list of arrivals using this stop
     */

    public List<Arrival> getArrivals() {
        return Collections.unmodifiableList(arrivals);
    }

    /**
     * Add route to set of routes with stops at this stop.
     *
     * @param route the route to add
     */
    public void addRoute(Route route) {
        routes.add(route);
        route.addStop(this);
    }

    /**
     * Remove route from set of routes with stops at this stop
     *
     * @param route the route to remove
     */
    public void removeRoute(Route route) {
        if (routes.contains(route)) {
            routes.remove(route);
            route.removeStop(this);
        }
    }

    /**
     * Determine if this stop is on a given route
     *
     * @param route the route
     * @return true if this stop is on given route
     */
    public boolean onRoute(Route route) {
        for (Route next : routes) {
            if (next.equals(route))
                return true;
        }
        return false;
    }

    /**
     * Add bus arrival travelling on a particular route at this stop.
     * Arrivals are to be sorted in order by arrival time
     *
     * @param arrival the bus arrival to add to stop
     */
    public void addArrival(Arrival arrival) {
        int index = 0;
        for (Arrival a: arrivals) {
            if (a.compareTo(arrival) <= 0 )
                index ++;
        }
        arrivals.add(index,arrival);
    }

    /**
     * Remove all arrivals from this stop
     */
    public void clearArrivals() {
        arrivals.clear();
    }

    /**
     * Two stops are equal if their ids are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop that = (Stop) o;

        if (!(getNumber() == that.getNumber())) return false;

        return true;
    }

    /**
     * Two stops are equal if their ids are equal.
     * Therefore hashCode only pays attention to number.
     */
    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public Iterator<Arrival> iterator() {
        // Do not modify the implementation of this method!
        return arrivals.iterator();
    }

    /**
     * setter for name
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for location
     *
     * @param locn the new location
     */
    public void setLocn(LatLon locn) {
        this.locn = locn;
    }
}
