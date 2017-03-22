package simplegaprule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

public class Reservation implements Intervaled {
	private int campsiteId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private DateTime startDate, endDate;
	
	public Reservation() {
		
	}
	
	public int getCampsiteId() {
		return campsiteId;
	}
	
	public void setCampsiteId(int campsiteId) {
		this.campsiteId = campsiteId;
	}
	
	@Override
	public DateTime getStartDate() {
		return startDate;
	}
	
	@Override
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public DateTime getEndDate() {
		return endDate;
	}
	
	@Override
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Check if this Reservation is the closest to the given search interval in comparison
	 * to all of the given reservations.
	 * 
	 * @param allReservations The reservations to compare against.
	 * @param search The interval to check if is adjacent to.
	 * @return Whether or not this reservation is adjacent to the Search.
	 */
	public boolean isAdjacent(List<Reservation> allReservations, Search search) {
		return !doesOverlap(search) && getGap(search) < allReservations.stream()
			.filter(x -> x != this) // Do not compare to self
			.filter(x -> !doesConflictReservation(search)) // Filter out conflicts with desired time
			.filter(x -> isBefore(search) == x.isBefore(search)) // Only compare distance if on same side
			.map(x -> x.getGap(search))
			.min(Integer::compareTo) // Get closest to the Search value
			.orElse(Integer.MAX_VALUE); // If no other reservation on same side, return obviously true value.
	}
	
	public boolean doesConflictReservation(Intervaled other) {
		return getInterval().overlaps(other.getInterval());
	}
	
	/**
	 * Check if this Reservation violates any of the given gap rules in
	 * relation to the given Search time interval.
	 * 
	 * @param rules The rules that specify the sizes of the gaps.
	 * @param search The interval to compare the gaps against.
	 * @return Whether any gap rules were violated.
	 */
	public boolean doesViolatesAnyGapRules(GapRule[] rules, Search search) {
		return Arrays.stream(rules).anyMatch(x -> doesViolatesGapRule(x, search));
	}
	
	/**
	 * Check if this Reservation violates the gap rule in relation to the
	 * given Search time interval.
	 * 
	 * @param rule The gap rule that specifies the size of the gap.
	 * @param search The interval to compare the gap against.
	 * @return Whether the gap rule is violated.
	 */
	public boolean doesViolatesGapRule(GapRule rule, Search search) {
		return search.getInterval().gap(getInterval()).toPeriod().getDays() == rule.getGapSize();
	}
}