package com.example.smarttone;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.location.DetectedActivity;

public class Constants {
	//Constants related to calander information
	public static long calender_read_duration = 10000;
	
	public static final Map<String, Integer> eventLevels = new HashMap<String, Integer>();
	public static final Map<String, Integer> activityLevels = new HashMap<String, Integer>();
	
	static{
		eventLevels.put("conference", 1);
		eventLevels.put("meeting", 7);
		eventLevels.put("lecture", 1);
		eventLevels.put("concert", 7);
		eventLevels.put("movie", 5);
		eventLevels.put("drama", 5);
		eventLevels.put("party", 7);
		
		
		activityLevels.put("in_vehicle",4);
		activityLevels.put("on_bicycle",5);
		activityLevels.put("on_foot",6);
		activityLevels.put("still",7);
		activityLevels.put("unknown",2);
		activityLevels.put("tilting",1);
	}
	public static final String NOISE="noise level Adapter";
	public static final String CALENDER_TASK="calender timer task";
	public static final String GPS_TASK="GPS task";
	public static final String ACTIVIY_TASK="activity task";
	
}
