package com.vg.hnc.rules;

import android.graphics.Color;

/**
 * Enum type to holds all the possible restrictions
 * A restriction is composed by two value, a color and a String
 * @author vincent
 */
public enum HNCRestriction {
	YELLOW (Color.YELLOW, "5-6"),
	PINK (Color.argb(255, 255, 192, 203), "7-8"),
	RED (Color.RED, "3-4"),
	GREEN (Color.argb(255, 0, 154, 0), "1-2"),
	BLUE (Color.argb(255, 51, 204, 255), "9-0"),
	NONE (Color.WHITE, "OK");
	
	/**
	 * Color of the restriction
	 */
	final private int color;
	/**
	 * String to hold the last two digits 
	 */
	final private String lastDigits;
	
	private HNCRestriction(int color, String lastDigits) {
		this.color = color;
		this.lastDigits = lastDigits;
	}

	public int getColor() {
		return color;
	}

	public String getLastDigits() {
		return lastDigits;
	}
}
