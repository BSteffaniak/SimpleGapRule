package simplegaprule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;
import simplegaprule.SimpleGapRuleProgram;

public class Search implements Intervaled {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private DateTime startDate, endDate;
	
	public Search() {
		
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
}