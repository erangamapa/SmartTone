package com.example.smarttone;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Handler mHandler = new Handler();
        Runnable mUpdateTimeTask = new Runnable() {
     	   public void run() {
     	        Intent i = new Intent(MainActivity.this, FullscreenActivity.class);     
     	        startActivity(i);
     	        finish();
     	   }
        };
        mHandler.postDelayed(mUpdateTimeTask, 1000);
        closeActivity.run();

        



    }
    



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
