package simplegaprule.models;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import simplegaprule.SimpleGapRuleProgram;

public interface Intervaled {
	DateTime getStartDate();
	DateTime getEndDate();
	
	void setStartDate(DateTime start);
	void setEndDate(DateTime start);
	
	default Interval getInterval() {
		return new Interval(getStartDate(), getEndDate());
	}
	
	default boolean isBefore(Intervaled other) {
		return getInterval().isBefore(other.getInterval());
	}
	
	default boolean isAfter(Intervaled other) {
		return getInterval().isAfter(other.getInterval());
	}
	
	default boolean doesOverlap(Intervaled other) {
		return getInterval().overlaps(other.getInterval());
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