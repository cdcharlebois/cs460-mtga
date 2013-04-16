package com.example.majortourguideapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class SplashScreen extends Activity implements OnClickListener {
	private Button btnGPS, btnContinue, btnInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		//inflate
		btnGPS = (Button)findViewById(R.id.btnGPS);
		btnContinue = (Button)findViewById(R.id.btnContinue);
		btnInfo = (Button)findViewById(R.id.btnInfo);
		
		//set listeners
		btnGPS.setOnClickListener(this);
		btnContinue.setOnClickListener(this);
		btnInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnContinue:
			//if GPS is not enabled, prompt to enable
			
			// --- /prompt ---
			goToNext();
			break;
		case R.id.btnGPS:
			//prompt to turn on GPS
			turnGPSOn();
			break;
		case R.id.btnInfo:
			//do some stuff
			//show about us screen (new activity or change the content of this one)
			break;
		}
		
	}
	
	private void goToNext(){
		Intent i = new Intent();
       	i.setComponent(new ComponentName("com.example.majortourguideapp",
       			"com.example.majortourguideapp.WelcomeMenu"));
      	startActivity(i);
	}
	
	/**
	 * from: http://stackoverflow.com/questions/4721449/enable-gps-programatically-like-tasker
	 */
	private void turnGPSOn(){
	    String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	    if(!provider.contains("gps")){ //if gps is disabled
	        final Intent poke = new Intent();
	        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	        poke.setData(Uri.parse("3")); 
	        sendBroadcast(poke);
	    }
	}
}
