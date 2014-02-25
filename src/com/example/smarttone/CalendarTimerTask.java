package com.example.smarttone;

import java.util.Iterator;
import java.util.Set;
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
		// TODO Auto-generated method stub
		AudioManager audioManager =  (AudioManager)this.mContext.getSystemService(Context.AUDIO_SERVICE);
		ContentResolver contentResolver = mContext.getContentResolver(); 
		long ntime = System.currentTimeMillis();
		 Cursor cursr = contentResolver.query(Uri.parse("content://com.android.calendar/events"), 
		 new String[]{ "calendar_id", "title", "description", "dtstart", "dtend", "eventLocation" }, 
		 "calendar_id=1", null, null); 
		 cursr.moveToFirst(); 
		 for (int i = 0; i < cursr.getCount(); i++) {
			String desc = cursr.getString(1).toLowerCase();
			if ((cursr.getLong(3) < ntime) && (ntime < cursr.getLong(4))) {
				String[] parts = desc.split("\\s+");
				for(String item : parts){
					if(Constants.eventLevels.get(item) != null){
						audioManager.setStreamVolume(AudioManager.STREAM_RING, Constants.eventLevels.get(item), AudioManager.FLAG_SHOW_UI);
					}
				}
            } 
			cursr.moveToNext();
		}
	}
	
	public void setContext(Context context){
		this.mContext = context;
	}

}
