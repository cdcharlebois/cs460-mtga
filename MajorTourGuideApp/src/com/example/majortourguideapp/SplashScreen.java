/**	====== ================== ======
 * 	------	SPLASHSCREEN.JAVA ------
 * 	====== ================== ======
 * 
 * 	=== MODIFICATIONS ===
 * 	04/17/2013 -> added logo to layout, added new buttons, attempted to animate
 */

package com.example.majortourguideapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SplashScreen extends Activity implements OnClickListener {
	private Button btnContinue, btnInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		//inflate
		
		btnContinue = (Button)findViewById(R.id.btnContinue);
		btnInfo = (Button)findViewById(R.id.btnInfo);
		
		//set listeners
		btnContinue.setOnClickListener(this);
		btnInfo.setOnClickListener(this);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onClick(View v) {
		/* --- cdc attempt at faking animation --- */
		v.setBackground(getResources().getDrawable(R.drawable.button_pressed));
		/*for(int i=0; i<100000; i++){
			float nothing = 3.145f / (float)Math.random()+1;	//100k flops -- just to kill time
			Log.i("cdc", "hello "+i);
		}*/
		v.setBackground(getResources().getDrawable(R.drawable.button));
		/* --- /attempt -- */
		switch(v.getId()){
		case R.id.btnContinue:
			
			
			//if GPS is not enabled, prompt to enable
			
			// --- /prompt ---
			goToNext();
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
