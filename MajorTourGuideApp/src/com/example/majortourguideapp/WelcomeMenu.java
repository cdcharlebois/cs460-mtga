package com.example.majortourguideapp;



import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;

public class WelcomeMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome_menu, menu);
		next();
		return true;
	}
	
	
	/**
	 * This method is called when the user selects a major
	 * @param int major: the major_id of the selected major 
	 * 		=> CIS=1, ACCOUNTING=2, FINANCE=3, MARKETING=4
	 */
	public void next(/*int major*/){
		//explicit intent for an Activity in another application
        //format is ComponentName(package, class)
        //be sure other app has been run at least once so that its package
        //is in the file system.
       	Intent i = new Intent();
       	i.setComponent(new ComponentName("com.example.majortourguideapp",
       			"com.example.majortourguideapp.Fork"));

       	// ========== vvv ==========
       	// this line sends the major selected to the next activity
       	i.putExtra("major", 1/* major */);	//change to whatever major they select.
        // ========== ^^^ ==========
       	
       	startActivity(i);
	}

}
