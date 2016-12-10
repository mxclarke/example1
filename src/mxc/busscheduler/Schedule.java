package mxc.busscheduler;

import java.time.LocalTime;

/**
 * <p>A time schedule for a bus.</p>
 * 
 * <p>The time schedule implements Comparable so that sequences can
 * be ordered, if required.</p>
 * 
 * <p>This class could, if necessary, be changed to an interface with a 
 * DefaultSchedule implementation.</p>
 * 
 * @author Moira
 */
public class Schedule implements Comparable<Schedule> {
	
	/**
	 * The start of the schedule, which also serves as an identifier.
	 * This is immutable but should not be unique, since we could have
	 * multiple schedules that start at the same time.
	 */
	private final LocalTime arrivalAtFirstStop;

	public Schedule(final LocalTime arrivalAtFirstStop) {
		this.arrivalAtFirstStop = arrivalAtFirstStop;
	}

	/**
	 * @return the identifying arrivalAtFirstStop, which is the
	 * starting point of the schedule
	 */
	public LocalTime getArrivalAtFirstStop() {
		return arrivalAtFirstStop;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Schedule schedule) {
		return this.arrivalAtFirstStop.compareTo(schedule.arrivalAtFirstStop);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Schedule [arrivalAtFirstStop=" + arrivalAtFirstStop + "]";
	}	
}
