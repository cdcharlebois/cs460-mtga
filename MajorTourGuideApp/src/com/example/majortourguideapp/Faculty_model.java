package com.example.majortourguideapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class Faculty_model {
	private String name;
	private String email;
	private String phone;
	private String building;
	private String room;
	private String dept;
	private String link;
	private String picture;
	private String dept_string;
	private Drawable picture_draw;

	public Faculty_model(String name, String email, String phone, 
			String building, String room, String dept, String link, Drawable picture_d, String dept_name){
		setName(name);
		setEmail(email);
		setPhone(phone);
		setBuilding(building);
		setRoom(room);
		setDept(dept);
		setLink(link);
		//setPicture(picture);
		setPicture_draw(picture_d);
		setDept_string(dept_name);
	}

	public String getDept_string() {
		return dept_string;
	}

	public void setDept_string(String dept_string) {
		this.dept_string = dept_string;
	}

	public Drawable getPicture_draw() {
		return picture_draw;
	}

	public void setPicture_draw(Drawable picture_draw) {
		this.picture_draw = picture_draw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getDeptString(){
		return this.dept_string;
				
	}
	
}
