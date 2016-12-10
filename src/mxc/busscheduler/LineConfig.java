package mxc.busscheduler;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * An internal class that maps a bus line to a series of schedule
 * and bus pairs. As this is a many-to-many relationship, in a 
 * relational database this would probably be represented as a 
 * join table, composed entirely of foreign keys.
 * 
 * @author Moira
 */
class LineConfig {
	
	/**
	 * A time segment (schedule) for a bus. 
	 */
	private static class ScheduleConfig {
		Schedule schedule;
		Bus bus;
		
		ScheduleConfig(Schedule schedule, Bus bus) {
			this.schedule = schedule;
			this.bus = bus;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ScheduleConfig [schedule=" + schedule + ", bus=" + bus + "]";
		}
	}
	
	private final Line line;
	
	private Map<LocalTime, ScheduleConfig> busSchedules = new HashMap<>();
	
	public LineConfig(final Line line) {
		this.line = line;
	}
	
	public void addBusAndSchedule(Schedule schedule, Bus bus) {
		ScheduleConfig config = new ScheduleConfig(schedule, bus);
		busSchedules.put(schedule.getArrivalAtFirstStop(), config);
	}
	
	public Line getLine() {
		return line;
	}
	
	public Bus findBusByStartTime(LocalTime startTime) {
		ScheduleConfig config = busSchedules.get(startTime);
		if ( config != null ) {
			return config.bus;
		} else {
			return null;
		}
	}

}
