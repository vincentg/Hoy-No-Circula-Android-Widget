package com.vg.hnc.rules;

import java.util.Calendar;


/**
 * Compute the restriction given a Calendar
 * @author vincent
 *
 */
public class HNCCompute {

	private final Calendar cal;
	private final int dayOfWeek;
	
	/**
	 * HNCCompute constructor
	 * @param calendar Instance of Calendar
	 */
	public HNCCompute(Calendar calendar) {
		this.cal = calendar;
		this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * Get the restriction for the current Calendar day
	 * @return HNCRestriction
	 */
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
	
	/**
	 * Get the restriction for the next Calendar day
	 * @return HNCRestriction
	 */
	public HNCRestriction getTomorrow() {
		return getRestrictionInXDays(1);
	}
	
	/**
	 * Get the restriction for the next Saturday
	 * @return HNCRestriction
	 */
	public HNCRestriction getNextSaturday() {
		int daysToAdd;
		if (dayOfWeek == Calendar.SATURDAY) {
			daysToAdd = 7;
		} else {
			daysToAdd = (Calendar.SATURDAY - dayOfWeek);
		}
		return getRestrictionInXDays(daysToAdd);
	}
	
	/**
	 * Get the restriction for a day in the future
	 * @param days number of days in the future
	 * @return HNCRestriction
	 */
	private HNCRestriction getRestrictionInXDays(int days) {
		Calendar newCal = Calendar.getInstance();
		newCal.add(Calendar.DAY_OF_YEAR, days);
		return new HNCCompute(newCal).getDay();
	}
}
