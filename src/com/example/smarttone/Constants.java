package com.example.smarttone;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	//Constants related to calander information
	public static long calender_read_duration = 10000;
	public static final Map<String, Integer> eventLevels = new HashMap<String, Integer>();
	static{
		eventLevels.put("conference", 1);
		eventLevels.put("meeting", 7);
		eventLevels.put("lecture", 1);
		eventLevels.put("concert", 7);
		eventLevels.put("movie", 5);
		eventLevels.put("drama", 5);
		eventLevels.put("party", 7);
	}
}
