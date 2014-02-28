package com.example.smarttone;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class Parameters {
	//Constants related to calander information
	public static long calender_read_duration = 10000;
	public static final Map<String, Integer> eventLevels = new HashMap<String, Integer>();
	public static void setEventLevels(){
		int maxVol = VolumeHandler.getMaxVolLevel();
		eventLevels.put("conference", 1);
		eventLevels.put("meeting", 1);
		eventLevels.put("lecture", 1);
		eventLevels.put("concert", maxVol);
		eventLevels.put("movie", maxVol - 2);
		eventLevels.put("drama", maxVol - 2);
		eventLevels.put("party", maxVol);
	}
}
