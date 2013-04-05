/*
 * CustomAdaper.java
 * Description: An example custom Adaptor to use with ListViews.
 * Author: Nick Charlton
 * Date: 8/5/11
 * License: MIT
 * 	=== MODIFICATIONS ===
 * 	04/05/2013 => Conner Charlebois
 * 					-> added note for Curtis to show where to update the linkage
 */

package com.example.majortourguideapp;

import java.util.ArrayList;


import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class CustomAdapter extends BaseAdapter {
	// store the context (as an inflated layout)
	private LayoutInflater inflater;
	// store the resource (typically list_item.xml)
	private int resource;
	// store (a reference to) the data
	private ArrayList<Faculty_model> data;
	
	/**
	 * Default constructor. Creates the new Adaptor object to
	 * provide a ListView with data.
	 * @param context
	 * @param resource
	 * @param data
	 */
	public CustomAdapter(Context context, int resource, ArrayList<Faculty_model> data) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.resource = resource;
		this.data = data;
	}
	
	/**
	 * Return the size of the data set.
	 */
	public int getCount() {
		return this.data.size();
	}
	
	/**
	 * Return an object in the data set.
	 */
	public Object getItem(int position) {
		return this.data.get(position);
	}
	
	/**
	 * Return the position provided.
	 */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Return a generated view for a position.
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		// reuse a given view, or inflate a new one from the xml
		View view;
		 
		if (convertView == null) {
			view = this.inflater.inflate(resource, parent, false);
		} else {
			view = convertView;
		}
		
		// bind the data to the view object
		return this.bindData(view, position);	//CC -> see note below
	}
	
	/**
	 * Bind the provided data to the view.
	 * This is the only method not required by base adapter.
	 */
	/* ==============================================================+
	 * This stuff needs to be changed to pull from the Faculty_model |
	 * --------------------------------------------------------------+
	public View bindData(View view, int position) {
		// make sure it's worth drawing the view
		if (this.data.get(position) == null) {
			return view;
		}
		
		// pull out the object
		ListItem item = this.data.get(position);			// Curtis, note that this is pulling from ListItem, we need to get the info from Faculty_model,
															// which is stored in this.data (passed from Faculty.java)
		
		// extract the view object
		View viewElement = view.findViewById(R.id.title);
		// cast to the correct type
		TextView tv = (TextView)viewElement;
		// set the value
		tv.setText(item.title);
		
		
		viewElement = view.findViewById(R.id.office);
		tv = (TextView)viewElement;
		tv.setText(item.office);
		
		viewElement = view.findViewById(R.id.email);
		tv = (TextView)viewElement;
		tv.setText(item.email);
		
	
		
		// return the final view object
		return view;
		
	}
	======================= /need_change ===============================*/
}
