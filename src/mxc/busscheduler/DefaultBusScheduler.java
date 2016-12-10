package mxc.busscheduler;

import java.util.Arrays;
import java.util.Optional;

/**
 * Default implementation for a bus scheduler.
 * 
 * @see BusScheduler
 */
public final class DefaultBusScheduler implements BusScheduler {

	/** In-memory storage. */
	private final DataRepository dataRepo;
	
	public DefaultBusScheduler(final DataRepository dataRepo) {
		this.dataRepo = dataRepo;
	}
	
	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#addBus(mxc.busscheduler.Bus)
	 */
	@Override
	public void addBus(Bus bus) {
		dataRepo.addBus(bus);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#removeBus(mxc.busscheduler.Bus)
	 */
	@Override
	public void removeBus(Bus bus) {
		dataRepo.removeBus(bus.getId());
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#createStop(java.lang.String)
	 */
	@Override
	public Stop createStop(String code) {
		Stop stop = new Stop(code);
		dataRepo.addStop(stop);
		return stop;
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#createRoute(java.lang.String, mxc.busscheduler.Stop[])
	 */
	@Override
	public Route createRoute(String name, Stop... stops) {
		Route route = new Route(name, Arrays.asList(stops));
		dataRepo.addRoute(route);
		return route;
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#createLine(int, mxc.busscheduler.Route[])
	 */
	@Override
	public Line createLine(int number, Route... routes) {
		Line line = new DefaultLine(number, routes);
		
		dataRepo.addLine(line);
				
		return line;
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#createSimpleLine(int, mxc.busscheduler.Route, mxc.busscheduler.Route)
	 */
	@Override
	public Line createSimpleLine(int number, Route forwardRoute, Route returnRoute) {
		Line line = new SimpleLine(number, forwardRoute, returnRoute);
		
		dataRepo.addLine(line);
		
		return line;
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#assignBus(mxc.busscheduler.Bus, mxc.busscheduler.Line, mxc.busscheduler.Schedule)
	 */
	@Override
	public void assignBus(Bus bus, Line line, Schedule schedule) {
		// Query repo for an existing line config.
		Optional<LineConfig> opt = dataRepo.findLineConfigByNumber(line.getNumber());
		
		// If the config doesn't exist for this line, create and add a new one.
		LineConfig config = opt.isPresent() 
				? opt.get() 
				: dataRepo.addLineConfig(new LineConfig(line));
				
		// Add the bus and schedule pair.
		config.addBusAndSchedule(schedule, bus);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#findBusById(java.lang.String)
	 */
	@Override
	public Optional<Bus> findBusById(String id) {
		return dataRepo.findBusById(id);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#findStopByCode(java.lang.String)
	 */
	@Override
	public Optional<Stop> findStopByCode(String code) {
		return dataRepo.findStopByCode(code);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#findRouteByName(java.lang.String)
	 */
	@Override
	public Optional<Route> findRouteByName(String name) {
		return dataRepo.findRouteByName(name);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#findLineByNumber(int)
	 */
	@Override
	public Optional<Line> findLineByNumber(int number) {
		return dataRepo.findLineByNumber(number);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.BusScheduler#findBusByLineAndSchedule(int, mxc.busscheduler.Schedule)
	 */
	@Override
	public Optional<Bus> findBusByLineAndSchedule(int number, Schedule schedule) {
		
		// Fine the line config for the given line number.
		Optional<LineConfig> opt = dataRepo.findLineConfigByNumber(number);
		if ( opt.isPresent() ) {
			LineConfig config = opt.get();
			// Find the bus associated with the given schedule's start time.
			Bus bus = config.findBusByStartTime(schedule.getArrivalAtFirstStop());
			if ( bus != null ) {
				return Optional.of(bus);
			} else {
				return Optional.empty();   // no bus starting at that time
			}
		} else {
			return Optional.empty();
		}
	}
}
