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

	public static void setVolLevel(int level) {
		mgr.setStreamVolume(AudioManager.STREAM_RING, level, AudioManager.FLAG_SHOW_UI);
	}

	public static int getMaxVolLevel() {
		return maxVolLevel;
	}
}
