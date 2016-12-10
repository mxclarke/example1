package mxc.busscheduler;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class TestScheduler extends TestCase {
	
	private static final String[] BUSES = {"B1", "B2"};
	private static final String[] STOPS = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	private BusScheduler busScheduler;
	
	@Override
	protected void setUp() {
		DataRepository repo = new DefaultDataRepository();
		busScheduler = new DefaultBusScheduler(repo);
		
		for ( String bus : BUSES ) {
			busScheduler.addBus(new Bus(bus));
		}
			
		for ( String stop : STOPS ) {
			busScheduler.createStop(stop);
		}
	}
	
	@Test
	public void test() {
		System.out.println("test 1");
		
		Line ttgLine = createTTGLine();
		Line paradiseLine = createParadiseLine();
		
		// Some start times for the schedules
		Schedule _7_5 = new Schedule(LocalTime.of(7, 5));
		Schedule _8_30 = new Schedule(LocalTime.of(8, 30));
		Schedule _9_15 = new Schedule(LocalTime.of(9, 15));
		
		// Assign a bus to TTG line for 7:05am
		Optional<Bus> optionalBus0 = busScheduler.findBusById(BUSES[0]);
		Assert.assertTrue(optionalBus0.isPresent());
		busScheduler.assignBus(optionalBus0.get(), ttgLine, _7_5);
		
		// Assign a bus to Paradise line for 8:30am
		Optional<Bus> optionalBus1 = busScheduler.findBusById(BUSES[1]);
		Assert.assertTrue(optionalBus1.isPresent());
		busScheduler.assignBus(optionalBus1.get(), paradiseLine, _8_30);

		// Assign bus 0 to Paradise line for 9:15
		busScheduler.assignBus(optionalBus0.get(), paradiseLine, _9_15);
		
		// Check it.
		Optional<Bus> optionalScheduledBus = busScheduler
				.findBusByLineAndSchedule(ttgLine.getNumber(), _7_5);
		Assert.assertTrue(optionalScheduledBus.isPresent());
		Assert.assertEquals(BUSES[0], optionalScheduledBus.get().getId());
		
		optionalScheduledBus = busScheduler
				.findBusByLineAndSchedule(paradiseLine.getNumber(),  _8_30);
		Assert.assertTrue(optionalScheduledBus.isPresent());
		Assert.assertEquals(BUSES[1], optionalScheduledBus.get().getId());
		
		optionalScheduledBus = busScheduler
				.findBusByLineAndSchedule(paradiseLine.getNumber(),  _9_15);
		Assert.assertTrue(optionalScheduledBus.isPresent());
		Assert.assertEquals(BUSES[0], optionalScheduledBus.get().getId());


	}
	
	private Line createTTGLine() {
		Route forwardRoute = busScheduler.createRoute("TTG to City",
				busScheduler.findStopByCode("1").get(),
				busScheduler.findStopByCode("2").get(),
				busScheduler.findStopByCode("6").get(),
				busScheduler.findStopByCode("7").get()
				);
		Route returnRoute = busScheduler.createRoute("City to TTG",
				busScheduler.findStopByCode("7").get(),
				busScheduler.findStopByCode("6").get(),
				busScheduler.findStopByCode("2").get(),
				busScheduler.findStopByCode("1").get()
				);
		return busScheduler.createSimpleLine(542, forwardRoute, returnRoute);
		
	}
	
	private Line createParadiseLine() {
		Route forwardRoute = busScheduler.createRoute("Paradise to City",
				busScheduler.findStopByCode("3").get(),
				busScheduler.findStopByCode("4").get(),
				busScheduler.findStopByCode("6").get(),
				busScheduler.findStopByCode("7").get()
				);
		Route returnRoute = busScheduler.createRoute("City to Paradise",
				busScheduler.findStopByCode("7").get(),
				busScheduler.findStopByCode("6").get(),
				busScheduler.findStopByCode("4").get(),
				busScheduler.findStopByCode("3").get()
				);
		return busScheduler.createSimpleLine(502, forwardRoute, returnRoute);
	}

}
