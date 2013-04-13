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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;

public class CustomAdapter extends BaseAdapter {
	// store the context (as an inflated layout)
	private LayoutInflater inflater;
	// store the resource (typically list_item.xml)
	private int resource;
	// store (a reference to) the data
	private ArrayList<Faculty_model> data;
	private Context ctx;

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
		this.ctx = context;
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
		return this.bindData(view, position);	
	}

	/**
	 * Bind the provided data to the view.
	 * This is the only method not required by base adapter.
	 */
	public View bindData(View view, int position) {
		// make sure it's worth drawing the view
		if (this.data.get(position) == null) {
			return view;
		}

		// pull out the object
		Faculty_model item = this.data.get(position);			
		// which is stored in this.data (passed from Faculty.java)

		// extract the view object
		View viewElement = view.findViewById(R.id.title);
		// cast to the correct type
		TextView tv = (TextView)viewElement;
		// set the value
		tv.setText(item.getName());


		viewElement = view.findViewById(R.id.office);
		tv = (TextView)viewElement;
		tv.setText(item.getDeptString());

		
	

		viewElement = view.findViewById(R.id.list_image);
		ImageView iv = (ImageView)viewElement;
		iv.setImageDrawable(item.getPicture_draw());


		// return the final view object
		return view;

	}
	
	/* ======================================================================================+
	 * This is a terrible way to do this. This image loading needs to happen in a new thread |
	 * --------------------------------------------------------------------------------------+
	 */
	private Drawable getImg(String imageUrl){
		if (android.os.Build.VERSION.SDK_INT > 9) {
		      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		      StrictMode.setThreadPolicy(policy);
		}
		try {
			Drawable d = Drawable.createFromStream(((InputStream)new URL(imageUrl).openStream()),"test");
			return d; 
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}


}
