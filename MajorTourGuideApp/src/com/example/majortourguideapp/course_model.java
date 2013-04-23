package com.example.majortourguideapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class course_model {
	private String course_code;
	private String course_desc;
	private String separator;
	

	public course_model(String course_code, String  separator, String course_desc){
		setCourse_code(course_code);
		setSeparator(separator);
		setCourse_desc(course_desc);
	}

	public String getSeparator() {
		return separator;
	}
	
	public void setSeparator(String separator) {
		this.separator = " : ";
	}


	public String getCourse_desc() {
		return course_desc;
	}

	public void setCourse_desc(String course_desc) {
		this.course_desc = course_desc;
	}


	public String getCourse_code() {
		return course_code;
	}

	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}

	
}
