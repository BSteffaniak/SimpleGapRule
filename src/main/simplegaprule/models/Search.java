package simplegaprule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;
import simplegaprule.SimpleGapRuleProgram;

public class Search {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private DateTime startDate, endDate;
	
	public Search() {
		
	}
	
	public DateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	public DateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	
	public String getFormattedStartDate() {
		return startDate.toString(SimpleGapRuleProgram.DEFAULT_DATE_FORMAT);
	}
	
	public String getFormattedEndDate() {
		return endDate.toString(SimpleGapRuleProgram.DEFAULT_DATE_FORMAT);
	}
	
	public String getFormattedDateRange() {
		return getFormattedStartDate() + " to " + getFormattedEndDate();
	}
}