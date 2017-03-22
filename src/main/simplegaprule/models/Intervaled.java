package simplegaprule.models;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import simplegaprule.SimpleGapRuleProgram;

public interface Intervaled {
	DateTime getStartDate();
	DateTime getEndDate();
	
	void setStartDate(DateTime start);
	void setEndDate(DateTime start);
	
	/**
	 * Get the Joda Interval for this Intervaled instance.
	 * 
	 * @return The Joda Interval instance.
	 */
	default Interval getInterval() {
		return new Interval(getStartDate(), getEndDate());
	}
	
	/**
	 * Check if this interval is before the given interval.
	 *
	 * @param other The interval to compare to.
	 * @return true if this interval is before other.
	 */
	default boolean isBefore(Intervaled other) {
		return getInterval().isBefore(other.getInterval());
	}
	
	/**
	 * Check if this interval is after the given interval.
	 * 
	 * @param other The interval to compare to.
	 * @return true if this interval is after other.
	 */
	default boolean isAfter(Intervaled other) {
		return getInterval().isAfter(other.getInterval());
	}
	
	/**
	 * Check if this interval overlaps the other interval.
	 * 
	 * @param other The other interval to check if overlaps.
	 * @return true if this interval overlaps the other.
	 */
	default boolean doesOverlap(Intervaled other) {
		return getInterval().overlaps(other.getInterval());
	}
	
	/**
	 * Get the days in the gap between the two intervals.
	 * 
	 * @param other The interval to get the gap between.
	 * @return An int representation of the days gap.
	 */
	default int getGap(Intervaled other) {
		return getInterval().gap(other.getInterval()).toPeriod().getDays();
	}
	
	default String getFormattedStartDate() {
		return getStartDate().toString(SimpleGapRuleProgram.DEFAULT_DATE_FORMAT);
	}
	
	default String getFormattedEndDate() {
		return getEndDate().toString(SimpleGapRuleProgram.DEFAULT_DATE_FORMAT);
	}
	
	default String getFormattedDateRange() {
		return getFormattedStartDate() + " to " + getFormattedEndDate();
	}
}