package com.example.smarttone;


import com.example.smarttone.ActivityUtils.REQUEST_TYPE;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity implements OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_main);
        Switch s = (Switch) findViewById(R.id.switch4);

        if (s != null) {
            s.setOnCheckedChangeListener(this);
        }
        
        
        
        Thread closeActivity = new Thread(new Runnable() {
        	  @Override
        	  public void run() {
        	    try {
        	    	ImageView img = (ImageView)findViewById(R.id.imageView2);
        	        AnimationDrawable frameAnimation =    (AnimationDrawable)img.getDrawable();
        	        frameAnimation.setCallback(img);
        	        frameAnimation.setVisible(true, true);
        	        frameAnimation.start();
        	      // Do some stuff
        	    } catch (Exception e) {
        	      e.getLocalizedMessage();
        	    }
        	  }
        });
        VolumeHandler.init(getApplicationContext());
        Handler mHandler = new Handler();
        Runnable mUpdateTimeTask = new Runnable() {
     	   public void run() {
     	        Intent i = new Intent(MainActivity.this, FullscreenActivity.class);     
     	        startActivity(i);
     	        finish();
     	   }
        };
        mHandler.postDelayed(mUpdateTimeTask, 1000);
        closeActivity.start();

    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
      
    
  	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
		 if(isChecked) {
		     onStartUpdates();
		 } else {
		     //do stuff when Switch if OFF
		 }
		
	}

    
    
    /**************************************** Activity Recognition **************************************/
    
    // Store the current request type (ADD or REMOVE)
    private REQUEST_TYPE mRequestType;

    // Holds the ListView object in the UI
    private ListView mStatusListView;

    /*
     * Holds activity recognition data, in the form of
     * strings that can contain markup
     */
    private ArrayAdapter<Spanned> mStatusAdapter;

    /*
     *  Intent filter for incoming broadcasts from the
     *  IntentService.
     */
    IntentFilter mBroadcastFilter;

    // Instance of a local broadcast manager
    private LocalBroadcastManager mBroadcastManager;

    // The activity recognition update request object
    private DetectionRequester mDetectionRequester;

    // The activity recognition update removal object
    private DetectionRemover mDetectionRemover;
    
    
    /**
     * Verify that Google Play services is available before making a request.
     *
     * @return true if Google Play services is available, otherwise false
     */
    private boolean servicesConnected() {

        // Check that Google Play services is available
        int resultCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {

            // In debug mode, log the status
            Log.d(ActivityUtils.APPTAG, getString(R.string.play_services_available));

            // Continue
            return true;

        // Google Play services was not available for some reason
        } else {

            // Display an error dialog
            GooglePlayServicesUtil.getErrorDialog(resultCode, this, 0).show();
            return false;
        }
    }
    
    /**
     * Respond to "Start" button by requesting activity recognition
     * updates.
     * @param view The view that triggered this method.
     */
    public void onStartUpdates() {

        // Check for Google Play services
        if (!servicesConnected()) {

            return;
        }

        /*
         * Set the request type. If a connection error occurs, and Google Play services can
         * handle it, then onActivityResult will use the request type to retry the request
         */
        mRequestType = ActivityUtils.REQUEST_TYPE.ADD;

        // Pass the update request to the requester object
        mDetectionRequester.requestUpdates();
    }
    
    
    /**
     * Respond to "Stop" button by canceling updates.
     * @param view The view that triggered this method.
     */
    public void onStopUpdates() {

        // Check for Google Play services
        if (!servicesConnected()) {

            return;
        }

        /*
         * Set the request type. If a connection error occurs, and Google Play services can
         * handle it, then onActivityResult will use the request type to retry the request
         */
        mRequestType = ActivityUtils.REQUEST_TYPE.REMOVE;

        // Pass the remove request to the remover object
        mDetectionRemover.removeUpdates(mDetectionRequester.getRequestPendingIntent());

        /*
         * Cancel the PendingIntent. Even if the removal request fails, canceling the PendingIntent
         * will stop the updates.
         */
        mDetectionRequester.getRequestPendingIntent().cancel();
    }
    
    

}
