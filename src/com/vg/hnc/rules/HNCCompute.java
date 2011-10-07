package com.vg.hnc.rules;

import java.util.Calendar;

public class HNCCompute {

	Calendar cal;
	int dayOfWeek;
	
	public HNCCompute(Calendar calendar) {
		this.cal = calendar;
		this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public HNCRestriction getDay() {
		switch (dayOfWeek) {
			case Calendar.MONDAY:
				return HNCRestriction.YELLOW;
			case Calendar.TUESDAY:
				return HNCRestriction.PINK;
			case Calendar.WEDNESDAY:
				return HNCRestriction.RED;
			case Calendar.THURSDAY:
				return HNCRestriction.GREEN;
			case Calendar.FRIDAY:
				return HNCRestriction.BLUE;
			case Calendar.SATURDAY:
				int weekNumber = cal.get(Calendar.WEEK_OF_MONTH);
				return HNCRestriction.values()[weekNumber-1];
		}
		return HNCRestriction.NONE;
	}
	
	public HNCRestriction getTomorrow() {
		return getRestrictionInXDays(1);
	}
	
	public HNCRestriction getNextSaturday() {
		int daysToAdd;
		if (dayOfWeek == Calendar.SATURDAY) {
			daysToAdd = 7;
		} else {
			daysToAdd = (Calendar.SATURDAY - dayOfWeek);
		}
		return getRestrictionInXDays(daysToAdd);
	}
	
	private HNCRestriction getRestrictionInXDays(int days) {
		Calendar newCal = Calendar.getInstance();
		newCal.add(Calendar.DAY_OF_YEAR, days);
		return new HNCCompute(newCal).getDay();
	}
}
