package simplegaprule.models;

import simplegaprule.InvalidCampsiteException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Campsite {
	private int id;
	private String name;
	
	public Campsite() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get whether the given reservationTime Search parameter is available at this Campsite.
	 * 
	 * @param environment The environment that contains the campsite and reservations.
	 * @param reservationTime The time to check availability for. Start/end date
	 * @return true if is available at the requested reservationTime, else false
	 */
	public boolean isReservationTimeAvailable(CampspotEnvironment environment, Search reservationTime) {
		// Make sure the campsite exists within the environment that is being searched
		if (!Arrays.asList(environment.getCampsites()).contains(this)) {
			throw new InvalidCampsiteException();
		}
		
		// Fetch all reservations for the campsite
		List<Reservation> reservations = Arrays.stream(environment.getReservations())
			.filter(r -> r.getCampsiteId() == id)
			.collect(Collectors.toList());
		
		if (reservations.stream().anyMatch(x -> x.doesConflictReservation(reservationTime))) {
			return false; // already reserved during this time
		}
		
		// Get reservations adjacent to the target reservation time
		Stream<Reservation> adjacent = reservations.stream()
			.filter(x -> x.isAdjacent(reservations, reservationTime));
		
		// Make sure none violate any existing gap rules
		return adjacent.noneMatch(x -> x.doesViolatesAnyGapRules(environment.getGapRules(), reservationTime));
	}
	
	public String toString() {
		return '"' + getName() + '"';
	}
}