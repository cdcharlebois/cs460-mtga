/**	====== ================== ======
 * 	------	SPLASHSCREEN.JAVA ------
 * 	====== ================== ======
 * 
 * 	=== MODIFICATIONS ===
 * 	04/17/2013 -> added logo to layout, added new buttons, attempted to animate
 * 	04/17/2013 -> added succesful animations, about us dialog
 * 
 */

package com.example.majortourguideapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import android.text.Html;
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
		switch(v.getId()){
		case R.id.btnContinue:
			if(!GPSIsEnabled()){	//if gps isn't enabled
				enableGPS();		//prompt to enable
			}
			goToNext();				//attemp to start
			break;
		case R.id.btnInfo:
			//do some stuff
			//show about us screen (new activity or change the content of this one)
			new AlertDialog.Builder(this)
			.setTitle("About this App")
			.setMessage(Html.fromHtml("<b>ABOUT US</b><br>Bentley Major Tour Guide App<br>Version: 2.0.1<br>Update:  1<br><b>HOW TO</b><br>Our app is simple to use. Please do the following:<br>Step 1. Choose your major<br>Step 2. Choose from three options<br> a. Find Location - Finds point on campus according to your major<br> b. Find Faculty - Finds information for faculty members<br> c. Major Requirements - Lists course requirements for your major<br>Step 3. Explore!<br><b>THE TEAM</b><br>Conner Charlebois - Lead Analyst<br>Robert Dextradeur - QA Manager / Project Analyst<br>Kristen Manning - Document Manager<br>Kelsie Oltman - Project nalyst<br>Curtis Trueb - Project Analyst<br>David Vaudo - Project Manager<br>Anthony Wu - UI Designer / Project Analyst<br><b>WHY WE MADE IT</b><br>Many students, we have found, have trouble finding important information relevant to their majors.  For incoming students (e.g., freshmen and transfers) and students looking to switch their concentrations, this is especially true.  And that is why we created our application, to help each student with his/her transition!"))
			.setPositiveButton("Okay, got it!", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			})
			.create()
			.show();
			break;
		}

	}

	private void goToNext(){
		Intent i = new Intent();
		i.setComponent(new ComponentName("com.example.majortourguideapp",
				"com.example.majortourguideapp.WelcomeMenu"));
		if (GPSIsEnabled()){
			startActivity(i);
		}
		
	}

	/**
	 * from: http://mgmblog.com/2009/11/03/is-gps-on-if-not-allow-user-to-turn-it-on/
	 */
	private boolean GPSIsEnabled(){
		LocationManager alm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE );
		if(alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER )){
			//Toast.makeText( this, "GPS is already on", Toast.LENGTH_SHORT ).show();
			return true;
		}
		return false;
	}
	private void enableGPS(){
		Toast.makeText( this, "Please enable GPS, then touch back to return.", Toast.LENGTH_SHORT ).show();
		Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS );
		startActivity(myIntent);
	}


}
