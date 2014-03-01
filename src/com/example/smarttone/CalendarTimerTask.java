package com.example.smarttone;

import java.util.Map;
import java.util.TimerTask;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;

public class CalendarTimerTask extends TimerTask {
	Context mContext;
	@Override
	public void run() {
		ContentResolver contentResolver = mContext.getContentResolver(); 
		long ntime = System.currentTimeMillis();
		Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"), 
		(new String[] { "calendar_id", "title", "description", "dtstart", "dtend", "eventTimezone", "eventLocation" }), 
		"( dtstart <" + ntime + " and dtend >" + ntime + ")", null, "dtstart ASC");
		Parameters.setEventLevels();
		if(cursor.moveToFirst()){
			String title = cursor.getString(1).toLowerCase();
			for (Map.Entry<String, Integer> entry : Parameters.eventLevels.entrySet()) {
			    if(title.contains(entry.getKey())){
			    	VolumeHandler.setVolLevel(entry.getValue(),Constants.CALENDER_TASK);
			    }
			}
		}
		
	}
	
	public void setContext(Context context){
		this.mContext = context;
	}

}
