package com.example.smarttone;

import android.content.Context;
import android.media.AudioManager;

public class VolumeHandler {
	private static int maxVolLevel = 0;
	static Context mContext;
	static AudioManager mgr;

	public static void init(Context mContext) {
		VolumeHandler.mContext = mContext;
		mgr = (AudioManager) VolumeHandler.mContext.getSystemService(Context.AUDIO_SERVICE);
		maxVolLevel = mgr.getStreamMaxVolume(AudioManager.STREAM_RING);
	}

	public static void setVolLevel(int level, String type) {
		int levelToCommit = volumeLevel(level,type);
		mgr.setStreamVolume(AudioManager.STREAM_RING, levelToCommit, AudioManager.FLAG_SHOW_UI);
	}
	
	// select actuall volume level based on GPS,noise, calander
	private static int volumeLevel(int level, String type){
		return level;
	}
	

	public static int getMaxVolLevel() {
		return maxVolLevel;
	}
}
