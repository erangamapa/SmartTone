package com.example.smarttone;

import java.io.IOException;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;

public class NoiseLevelMonitor extends IntentService {
    

	public NoiseLevelMonitor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		MediaRecorder mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSamplingRate(5000);
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		mediaRecorder.setOutputFile("/dev/null");
		try {
			mediaRecorder.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaRecorder.start();
        mediaRecorder.getMaxAmplitude();	
		// Obtain maximum amplitude since last call of getMaxAmplitude()
		int count =0;
		double total=  0;
		while(count<1000) {	
		    double amplitude = mediaRecorder.getMaxAmplitude()*1.0f;
		    if(amplitude==0){
		    	continue;
		    }
			total= total+amplitude;			
			System.out.println(amplitude);
		    count++;	    
		}
		double avg = total/count;
		AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	double value =	Math.ceil((avg/35000)*audio.getStreamMaxVolume(audio.STREAM_RING));
	 int intval = (int)value;
	 	 
    //audio.setStreamVolume(audio.STREAM_RING, intval,0);
	VolumeHandler.setVolLevel(intval,Constants.NOISE);	
		
		
	//	Toast.makeText(getApplicationContext(),total , Toast.LENGTH_LONG).show();

		// Don't forget to release
	mediaRecorder.reset();
	mediaRecorder.release();	
		
	}
}