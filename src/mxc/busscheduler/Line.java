package mxc.busscheduler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>A bus line, identified by a number and includes a series of routes.</p>
 * 
 * <p>There are usually two routes -- forward and return -- although there could
 * be others (e.g. express, school, and other variations).</p>
 * 
 * @author Moira
 */
public interface Line {

	/**
	 * @return the bus line's identifier
	 */
	int getNumber();
	
	/**
	 * @return the routes on this bus line
	 */
	List<Route> getRoutes();
	
	/**
	 * Convenience method, for use of implementing classes, to create
	 * an unmodifiable list of routes from an argument array. Note that
	 * this will not be publicly exported since its access qualifier
	 * is package-private.
	 */
	static List<Route> createRoutes(final Route... routes) {
		List<Route> resultRoutes = Arrays.asList(routes);
		resultRoutes = Collections.unmodifiableList(resultRoutes);
		return resultRoutes;
	}
}
