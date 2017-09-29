package com.psci.astrea.screen.helper;

public class TimeHelper {

	/**
	 * @param timeInSec
	 * @return
	 */
	public static String formatSeconds(float timeInSec) {
		String time = "";
		float minute = timeInSec/60;
		float sec = timeInSec % 60;
		if(minute < 10)
			time += "0" + (int)minute;
		else
			time += (int)minute;
		
		time += ":";
		
		if(sec < 10)
			time += "0" + (int)sec;
		else
			time += (int)sec;
		
		return time;
	}
}
