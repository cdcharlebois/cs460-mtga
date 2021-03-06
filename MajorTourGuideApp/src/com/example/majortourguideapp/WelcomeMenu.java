/**	====== ================ ======
 * 	------ WELCOMEMENU.JAVA ------
 * 	====== ================ ======
 * 	This is the first screen users see when they open our app.
 * 	It prompts them to choose an available major, then proceeds to the next section.
 */
package com.example.majortourguideapp;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeMenu extends Activity implements OnClickListener {
	
	//Declare Instance Variables
	private Button btnCIS, btnFI, btnAC, btnMK; 
	private int major;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_menu);
		
		//inflate layout
		btnCIS = (Button) findViewById(R.id.btnCIS);
		
		btnFI = (Button) findViewById(R.id.btnFI);
		
		btnAC = (Button) findViewById(R.id.btnAC);
		
		btnMK = (Button) findViewById(R.id.btnMK);
		
		
		//set listeners
		btnCIS.setOnClickListener(this);
		btnFI.setOnClickListener(this);
		btnAC.setOnClickListener(this);
		btnMK.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_welcome_menu, menu);
		//next();
		return true;
	}
	/**
	 * This method is called when the user selects a major
	 * @param int major: the major_id of the selected major 
	 * 		=> CIS=1, ACCOUNTING=2, FINANCE=3, MARKETING=4
	 */
	public void next(int major){
		//explicit intent for an Activity in another application
        //format is ComponentName(package, class)
        //be sure other app has been run at least once so that its package
        //is in the file system.
       	Intent i = new Intent();
       	i.setComponent(new ComponentName("com.example.majortourguideapp",
       			"com.example.majortourguideapp.Fork"));

       	// ========== vvv ==========
       	// this line sends the major selected to the next activity
       	i.putExtra("major", major);	//change to whatever major they select.
        // ========== ^^^ ==========
       	
       	startActivity(i);
	}
	
	
	public void onClick(View v) {
//		String name = "";
//		Intent i = new Intent();
		
		//check to see if view is a button
		if(v instanceof Button){
			Log.i("cdc", "this is a button. It says " + ((Button)v).getText());
			//do some color stuff
			
		}
		
		switch (v.getId()){
		case R.id.btnCIS:
		
			next(1);
			break;
		case R.id.btnFI:
		
			next(3);
			break;
		case R.id.btnAC:
		
			next(2);
			break;
		case R.id.btnMK:
		
			next(4);
			break;
			}
	}
	


}
