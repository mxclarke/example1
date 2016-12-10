package mxc.busscheduler;

import java.util.Optional;

/**
 * Data storage. 
 * 
 * @author Moira
 *
 */
public interface DataRepository {
	
	/**
	 * Adds a bus to the store.
	 * 
	 * @param bus the new bus
	 */
	void addBus(Bus bus);
	
	/**
	 * Removes the bus with the given id, if it exists.
	 * 
	 * @param busId identifier for a bus
	 */
	void removeBus(String busId);
	
	/**
	 * Optionally returns a bus with the given id.
	 * 
	 * @param id identifier for a bus
	 * 
	 * @return the bus, or empty Optional if not found
	 */
	Optional<Bus> findBusById(String id);
	
	/**
	 * Adds a bus stop to the store. If a stop with the same code
	 * already exists in the store, it will be overwritten.
	 * 
	 * @param stop the new stop
	 */
	void addStop(Stop stop); 
	// TODO remove Stop(String code)
	
	/**
	 * Optionally returns a bus bus with the given code.
	 * 
	 * @param code identifier for a bus
	 * 
	 * @return the bus stop, or empty Optional if not found
	 */
	Optional<Stop> findStopByCode(String code);
	
	/**
	 * Adds a bus route to the store. If a route with the same name
	 * already exists in the store, it will be overwritten.
	 * 
	 * @param route newly created bus route
	 */
	void addRoute(Route route);
	// TODO remove Route(String name)
	
	/**
	 * Optionally returns a route with the given name.
	 * 
	 * @param name identifier for a route
	 * 
	 * @return the route, or empty Optional if not found
	 */
	Optional<Route> findRouteByName(String name);
	
	/**
	 * Adds a line, which consists of one or more routes.
	 * If a line with the same number already exists, it will be
	 * overwritten.
	 * 
	 * @param line the newly created line
	 */
	void addLine(Line line);
	// TODO remove Line(int number)
	
	/**
	 * Optionally returns a line with the given number.
	 * 
	 * @param name identifier for a line
	 * 
	 * @return the line, or empty Optional if not found
	 */
	Optional<Line> findLineByNumber(int number);
	
	/**
	 * Adds an internal aggregation object.
	 * 
	 * A line configuration allows us to map a bus line to a series of schedule
	 * and bus pairs.
	 * 
	 * @param lineConfig a line with a list of bus and schedule pairs
	 * 
	 * @return the original lineConfig parameter, to allow for chaining
	 */
	LineConfig addLineConfig(LineConfig lineConfig);
	
	/**
	 * Optionally returns an internal aggregation object with the given line number.
	 * 
	 * @param name identifier for a line
	 * 
	 * @return the line configuration, or empty Optional if not found
	 */
	Optional<LineConfig> findLineConfigByNumber(int number);
}
