/**
 * DB_Helper.java
 * @author CHARLEB_CONN
 * This class should be called in order to reference the DB.
 * [DONE] Add functionality to query here [CC]
 * [3/26/2013] EDIT: Added course table
 */



package com.example.majortourguideapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
	/* ----------
		Variables for helper
		---------- */
	public static final String DATABASE_NAME = "mtga.db";
	public static final int DATABASE_VERSION = 1;	//if you change the schema, you need to update this.
	
	/* ----------
		Variables for SQL
		---------- */
	public static final String TEXT_TYPE = " TEXT";
	public static final String COMMA_SEP = ", ";
	public static final String UNIQUE = " UNIQUE";
	public static final String NULL = " NULL";
	// --------------------
	//	CREATION 
	// --------------------
	public static final String SQL_CREATE_MAJORS = 			 
			"CREATE TABLE " + DB_Contract.Major.TABLE_NAME + " (" + 
			DB_Contract.Major._ID + " INTEGER PRIMARY KEY," + 
			DB_Contract.Major.COLUMN_MAJOR_ID + TEXT_TYPE + UNIQUE + COMMA_SEP +
			DB_Contract.Major.COLUMN_NAME + TEXT_TYPE + UNIQUE +
			") ";
	public static final String SQL_CREATE_DESTINATIONS = 			 
			"CREATE TABLE " + DB_Contract.Destination.TABLE_NAME + " (" + 
			DB_Contract.Destination._ID + " INTEGER PRIMARY KEY," + 
			DB_Contract.Destination.COLUMN_MAJOR + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Destination.COLUMN_LOCATION + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Destination.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Destination.COLUMN_ROOM + TEXT_TYPE + COMMA_SEP +	
			DB_Contract.Destination.COLUMN_BLURB + TEXT_TYPE +
			") ";
	public static final String SQL_CREATE_PROFESSORS = 
			"CREATE TABLE " + DB_Contract.Professor.TABLE_NAME + " ( " +
			DB_Contract.Professor._ID + " INTEGER PRIMARY KEY, "+ 
			DB_Contract.Professor.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP + 
			DB_Contract.Professor.COLUMN_PHONE + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_BUILDING + TEXT_TYPE + COMMA_SEP + 
			DB_Contract.Professor.COLUMN_ROOM + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_DEPARTMENT + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_LINK + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_PICTURE + TEXT_TYPE +
			");";
	public static final String SQL_CREATE_ENTRANCES = 
			"CREATE TABLE " + DB_Contract.Entrance.TABLE_NAME + " ( "+
			DB_Contract.Entrance._ID + " INTEGER PRIMARY KEY, " +
			DB_Contract.Entrance.COLUMN_LOCATION + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Entrance.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Entrance.COLUMN_LAT + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Entrance.COLUMN_LONG + TEXT_TYPE +
			");";
	public static final String SQL_CREATE_COURSES = 
			"CREATE TABLE " + DB_Contract.Course.TABLE_NAME + " ( " +
			DB_Contract.Course._ID + "INTEGER PRIMARY KEY" + COMMA_SEP +
			DB_Contract.Course.COLUMN_COURSE_CODE + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Course.COLUMN_COURSE_DESC + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Course.COLUMN_MAJOR_LINK + TEXT_TYPE +
			");";
			
			
	// --------------------
	//	MAJOR
	// --------------------
	public static final String SQL_DELETE_MAJORS = 
			"DROP TABLE IF EXISTS " + DB_Contract.Major.TABLE_NAME;
	public static final String SQL_POPULATE_MAJORS = 
			"INSERT INTO " + DB_Contract.Major.TABLE_NAME + " ( "+ DB_Contract.Major.COLUMN_MAJOR_ID +", " + DB_Contract.Major.COLUMN_NAME +") VALUES" +
					"('1','CIS'), " +
					"('2','ACCOUNTING'), " +
					"('3','FINANCE'), " +
					"('4','MARKETING');"; 
	
	//	--------------------
	//		ENTRANCE
	//	--------------------
	public static final String SQL_POPULATE_ENTRANCES =
			"INSERT INTO " + DB_Contract.Entrance.TABLE_NAME + "("+DB_Contract.Entrance.COLUMN_LOCATION+", "+DB_Contract.Entrance.COLUMN_NAME + ", " + DB_Contract.Entrance.COLUMN_LAT +", " + DB_Contract.Entrance.COLUMN_LONG + ") VALUES"+
			"('101','SMITH','42.38753','-71.2204'), "+
			"('102','JENISON','42.38788','-71.2205'), "+
			"('104','ADAMIAN','42.38707','-71.21899'), "+
			"('105','LIBRARY','42.38781','-71.22008'), "+
			"('106','MORISON','42.38785','-71.21915'), "+
			"('107','LINDSAY','42.38747','-71.21965'); ";
	public static final String SQL_DELETE_ENTRANCES = 
			"DROP TABLE IF EXISTS " + DB_Contract.Entrance.TABLE_NAME;
	//	--------------------
	//		DESTINATION
	//	--------------------
	
public static final String SQL_POPULATE_DESTINATIONS = 
"INSERT INTO " + DB_Contract.Destination.TABLE_NAME + " (" +DB_Contract.Destination.COLUMN_MAJOR +", "+DB_Contract.Destination.COLUMN_LOCATION+", "+ DB_Contract.Destination.COLUMN_NAME +", "+DB_Contract.Destination.COLUMN_ROOM  +", "+DB_Contract.Destination.COLUMN_BLURB  +" ) VALUES"+
"('1', '105', 'Library', '', ''),"+
"('1', '107', 'Academic advising', '21', ''),"+
"('1', '101', 'CIS Department Chair', '420', 'CIS Department Chair, Douglass Robertson, is located on the fourth floor of Smith. After entering the building, continue left towards the Smith elevator/stairs. After traveling up to the fourth floor, continue straight ahead to room 420.'),"+
"('1', '101', 'CIS Sandbox', '234', 'The CIS Sandbox is located on the seond floor of Smith. After entering the building, take the stairs down one flight and head left towards the Bentley Trading Room. Once at the Trading Room, continue left to arrive at the CIS Sandbox located in Smith 234.'),"+
"('2', '105', 'Library', '', ''),"+
"('2', '107', 'Academic Advising', '21', ''),"+
"('2', '104', 'Accounting Department Chair', '212', 'Accounting Department Chair, Mark Nixon, is located on the second floor of Adamian. After entering the building, continue to the stairs located on the right side of the lobby. Once at the second floor, continue straight about 50 ft to the Accounting Department located on the left. Mark Nixons office is located in room 212'),"+
"('2', '102', 'ACELAB', '300', 'The ACELAB is located on the third floor of Jennison. After entering the building, turn left towards the Jennison elevator and continue straight to the ACELAB located in Jennison 300.'),"+
"('3', '105', 'Library', '', ''),"+
"('3', '107', 'Academic Advising', '21', ''),"+
"('3', '104', 'Finance Department Chair', '215', 'Finance Department Chair, Atul Gupta, is located on the second floor of Adamian. After entering the building, continue to the stairs located on the right side of the lobby. Once at the second floor, continue straight about 50ft to the Finance Department located on the right. Atul Guptas office is located in room 215'),"+
"('3', '101', 'Trading Room', '226', 'The Bentley Trading Room is located on the second floor of Smith. After entering the building, take the stairs down one flight and turn left to arrive at the Bentley Trading Room. '),"+
"('4', '105', 'Library', '', ''),"+
"('4', '107', 'Academic Advising', '21', ''),"+
"('4', '106', 'Marketing Department Chair', '216', 'Marketing Department Chair, Andrew Aylesworth, is located on the second floor of Morison. After entering the building, continue straight, traveling past the stairs, to arrive at Morison 216'),"+
"('4', '106', 'CMT', '220', 'The CMT is located on the second floor of Morison. After entering the building, continue straight to arrive at the CMT located on the right.')";

	public static final String SQL_DELETE_DESTINATIONS = 
			"DROP TABLE IF EXISTS " + DB_Contract.Destination.TABLE_NAME;
			
	//	--------------------
	//		PROFESSOR
	//	--------------------
	public static final String SQL_POPULATE_PROFESSORS = 
			"INSERT INTO " + DB_Contract.Professor.TABLE_NAME + " ( "+ DB_Contract.Professor.COLUMN_NAME +", " + DB_Contract.Professor.COLUMN_EMAIL +", " + DB_Contract.Professor.COLUMN_PHONE +", " + DB_Contract.Professor.COLUMN_BUILDING +", " + DB_Contract.Professor.COLUMN_ROOM +", " + DB_Contract.Professor.COLUMN_DEPARTMENT +", " + DB_Contract.Professor.COLUMN_LINK +", " + DB_Contract.Professor.COLUMN_PICTURE +") VALUES "+
					"('Abbott, Melinda', 'mabbot@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=mabbott', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Amaral, Alicia', 'aamaral@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=aamaral', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Berube, Scott', 'sberube@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=sberube', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Beveridge, John', 'jbeveridge@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=jbeveridge', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Boulanger, David', 'tboulanger@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=dboulanger', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Buttacavoli, Thomas', 'tbuttacavoli@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=tbuttacavoli', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Cannon, Nathan', 'ncannon@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=ncannon', 'https://faculty.bentley.edu/storage/cannon_nath/pci/cannon-1.bmp'), "+
					"('Dugar, Amitabh', 'adugar@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=adugar', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Evans, Susan', 'sevans@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=sevans', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Grenier, Michael', 'mgrenier@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=mgreiner', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Gupta, Krishan', 'kgupta@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=kgupta', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Hanes, Denise', 'dhanes@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=dhanes', 'https://faculty.bentley.edu/storage/dhanes/pci/Hanes-Bentley-111013_072-1.JPG'), "+
					"('Hartt, Allen', 'ahartt@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=hartt_alle', 'https://faculty.bentley.edu/storage/hartt_shan/pci/Y10-119-20-RJ-1.jpg'), "+
					"('Hogan, John', 'jhogan@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=jlhogan', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Holderness, Darin', 'holdern_dar@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=holdern_dari', 'https://faculty.bentley.edu/storage/holdern_dari/pci/0bf4d9d-1.jpg'), "+
					"('Holmes, Gordon', 'gholmes@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=gholmes', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('McKinley, John', 'jmckinley@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=jmckinley', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Miller, Paul', 'pmiller@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=pmiller1', 'https://faculty.bentley.edu/storage/pmiller1/pci/pmiller-1.jpg'), "+
					"('Nolder, Christine', 'cnolder@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=cnolder', 'https://faculty.bentley.edu/storage/cnolder/pci/christine-nolder-1.jpg'), "+
					"('Pepe, Leonard', 'lpepe@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=lpepe', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Rowat, Chantal', 'crowat@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=crowat', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Seltz, William', 'wseltz@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=wseltz', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Spector, Cindy', 'cspector@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=cspector', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Speros, Jonathan', 'jsperos@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=jsperos', 'https://faculty.bentley.edu/storage/jsperos/pci/Jon%20Speros_%20Bentley2-1.jpg'), "+
					"('Spinace-Casale, Antonella', 'acasale@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=avogel', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('White, James', 'jwhite@bentley.edu', '7818912091', 'Adamian', '280', '2', 'https://faculty.bentley.edu/details.asp?uname=jfwhite', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Abdolmohammad, Mohammad', 'mabdolmohammad@bentley.edu', '7818912976', 'Morison', '324', '2', 'https://faculty.bentley.edu/details.asp?uname=mabdolmohamm', 'https://faculty.bentley.edu/storage/mabdolmohamm/pci/abdolmo-1.jpg'), "+
					"('Baxter, Ryan', 'rbaxter@bentley.edu', '7818913485', 'Adamian', '289', '2', 'https://faculty.bentley.edu/details.asp?uname=rbaxter', 'https://faculty.bentley.edu/storage/rbaxter/pci/Ryan-Baxter-1.jpg'), "+
					"('Bedard, Jean', 'jbedard@bentley.edu', '7818912410', 'Adamian', '240', '2', 'https://faculty.bentley.edu/details.asp?uname=jbedard', 'https://faculty.bentley.edu/storage/jbedard/pci/Jean-Bedard-1.jpg'), "+
					"('Boss, Scott', 'sboss@bentley.edu', '7818912353', 'Adamian', '267', '2', 'https://faculty.bentley.edu/details.asp?uname=sboss', 'https://faculty.bentley.edu/storage/sboss/pci/Scott-Boss-1.jpg'), "+
					"('Bumbay, Priscilla', 'pbumbay@bentley.edu', '7818912519', 'Adamian', '214', '2', 'https://faculty.bentley.edu/details.asp?uname=pburnaby', 'https://faculty.bentley.edu/storage/pburnaby/pci/2024-1.jpg'), "+
					"('Cook, Edward', 'ecook@bentley.edu', '7818912091', 'Not provided', 'Not provided', '2', 'https://faculty.bentley.edu/details.asp?uname=ecook', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Fedorowicz, Jane', 'jfedorowicz@bentley.edu', '7818913153', 'Adamian', '206', '2', 'https://faculty.bentley.edu/details.asp?uname=jfedorowicz', 'https://faculty.bentley.edu/storage/jfedorowicz/pci/jane-2.jpg'), "+
					"('Feldman, Dorothy', 'dfeldman@bentley.edu', '7818912782', 'Morison', '305B', '2', 'https://faculty.bentley.edu/details.asp?uname=dfeldmann', 'https://faculty.bentley.edu/storage/dfeldmann/pci/dorothy%20%20feldman-1.jpg'), "+
					"('Fionda, David', 'dfionda@bentley.edu', '7818912091', 'Not provided', 'Not provided', '2', 'https://faculty.bentley.edu/details.asp?uname=dfionda', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Freed, Alan', 'afreed@bentley.edu', '7818912409', 'Morison', '149G', '2', 'https://faculty.bentley.edu/details.asp?uname=afreed', 'https://faculty.bentley.edu/storage/afreed/pci/afreed-1.jpg'), "+
					"('Graham, Lynford', 'lgraham@bentley.edu', '7818912091', 'Not provided', 'Not provided', '2', 'https://faculty.bentley.edu/details.asp?uname=lgraham', 'https://faculty.bentley.edu/storage/lgraham/pci/Graham%20Pict%205-1.jpg'), "+
					"('Gujarathi, Mahendra', 'mgujarathi@bentley.edu', '7818913408', 'Adamian', '218', '2', 'https://faculty.bentley.edu/details.asp?uname=mgujarathi', 'https://faculty.bentley.edu/storage/mgujarathi/pci/mgujarathi-1.jpg'), "+
					"('Haselkom, Michael', 'mhaskelkom@bentley.edu', '7818912521', 'Adamian', '216', '2', 'https://faculty.bentley.edu/details.asp?uname=mhaselkorn', 'https://faculty.bentley.edu/storage/mhaselkorn/pci/mhaselkorn-1.jpg'), "+
					"('Hoitash, Ran', 'rhoitash@bentley.edu', '7818912588', 'Adamian', '279', '2', 'https://faculty.bentley.edu/details.asp?uname=rhoitash', 'https://faculty.bentley.edu/storage/rhoitash/pci/Rani-Hoitash-1.jpg'), "+
					"('Howe, Martha', 'mhowe@bentley.edu', '7818912573', 'Adamian', '220', '2', 'https://faculty.bentley.edu/details.asp?uname=mhowe', 'https://faculty.bentley.edu/storage/mhowe/pci/400911-1.jpg'), "+
					"('Kelly, Doris', 'dkelly@bentley.edu', '7818912412', 'Smith', '310', '2', 'https://faculty.bentley.edu/details.asp?uname=dkelly', 'https://faculty.bentley.edu/storage/dkelly/pci/Allisons%20graduation%20006-1.JPG'), "+
					"('Levesque, Raymond', 'rlevesque@bentley.edu', '7818913486', 'Morison', '378', '2', 'https://faculty.bentley.edu/details.asp?uname=rlevesque', 'https://faculty.bentley.edu/storage/rlevesque/pci/2438-1.jpg'), "+
					"('Lindblom, Cristi', 'clindblom@bentley.edu', '7818912499', 'Adamian', '210', '2', 'https://faculty.bentley.edu/details.asp?uname=bmaciver', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('MacIver, Brian', 'bmaciver@bentley.edu', '7818912134', 'Morison', '282', '2', 'https://faculty.bentley.edu/details.asp?uname=bmaciver', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Malgwi, Charles', 'cmalgwi@bentley.edu', '7818912774', 'Adamian', '285', '2', 'https://faculty.bentley.edu/details.asp?uname=cmalgwi', 'https://faculty.bentley.edu/storage/cmalgwi/pci/cmalgwi-1.jpg'), "+
					"('McConville, Donna', 'dmcconville@bentley.edu', '7818912433', 'Morison', '149A', '2', 'https://faculty.bentley.edu/details.asp?uname=dmcconville', 'https://faculty.bentley.edu/storage/dmcconville/pci/Donna%20profile%20for%20Bentley-1.jpg'), "+
					"('Nixon, Mark', 'mnixon@bentley.edu', '7818912087', 'Adamian', '212', '2', 'https://faculty.bentley.edu/details.asp?uname=mnixon', 'https://faculty.bentley.edu/storage/mnixon/pci/Mark_Nixon-1.jpg'), "+
					"('Noga, Tracy', 'ntracy@bentley.edu', '7818912432', 'Morison', '131', '2', 'https://faculty.bentley.edu/details.asp?uname=tnoga', 'https://faculty.bentley.edu/storage/tnoga/pci/tnoga-1.jpg'), "+
					"('Ostherheld, Karen', 'kostherheld@bentley.edu', '7818912724', 'Adamian', '282', '2', 'https://faculty.bentley.edu/details.asp?uname=kosterheld', 'https://faculty.bentley.edu/storage/kosterheld/pci/2627-1.jpg'), "+
					"('Read, William', 'wread@bentley.edu', '7818912525', 'Adamian', '222', '2', 'https://faculty.bentley.edu/details.asp?uname=wread', 'https://faculty.bentley.edu/storage/wread/pci/2683-1.jpg'), "+
					"('Reed, Arthur', 'areed@bentley.edu', '7818912323', 'Morison', '126', '2', 'https://faculty.bentley.edu/details.asp?uname=areed', 'https://faculty.bentley.edu/storage/areed/pci/areed-1.jpg'), "+
					"('Ruff, Michael', 'mruff@bentley.edu', '7818912402', 'Morison', '126', '2', 'https://faculty.bentley.edu/details.asp?uname=mruff', 'https://faculty.bentley.edu/storage/mruff/pci/Michael%20Ruff-1.jpg'), "+
					"('Schnader, Anne', 'aschnader@bentley.edu', '7818912439', 'Morison', '119', '2', 'https://faculty.bentley.edu/details.asp?uname=aschnader', 'https://faculty.bentley.edu/storage/aschnader/pci/anne_schnader-1.jpg'), "+
					"('Schwarzkopf, David', 'dschwarzkopf@bentley.edu', '7818912783', 'Adamian', '283', '2', 'https://faculty.bentley.edu/details.asp?uname=dschwarzkopf', 'https://faculty.bentley.edu/storage/dschwarzkopf/pci/david_schwarzkopf-1.jpg'), "+
					"('Thibodeau, Jay', 'jthibodeau@bentley.edu', '7818912134', 'Morison', '373', '2', 'https://faculty.bentley.edu/details.asp?uname=jthibodeau', 'https://faculty.bentley.edu/storage/jthibodeau/pci/Thibodeau%20Head%20Shot%20-%202012-1.jpg'), "+
					"('Willett, Laura', 'lwillet@bentley.edu', '7818912152', 'Adamian', '224', '2', 'https://faculty.bentley.edu/details.asp?uname=lwillett', 'https://faculty.bentley.edu/storage/lwillett/pci/765160-1.jpg'), "+
					"('Yezegel, Ari', 'ayezegel@bentley.edu', '7818912264', 'Adamian', '287', '2', 'https://faculty.bentley.edu/details.asp?uname=ayezegel', 'https://faculty.bentley.edu/storage/ayezegel/pci/ari-yezegel-1.jpg'), "+
					"('Aucoin, Donald', 'daucoin@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=daucoin', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Jones, Andrea', 'ajones@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=ajones', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Macarty, Matthew', 'mmacarty@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=mmacarty', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('McCarron, Elizabeth', 'emccarron@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=emccarron', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Nezuh, Joseph', 'jnezuh@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=jnezuh', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Rice, Patricia', 'price@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=price', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Rude, David', 'drude@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=drude', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Shah, Gauray', 'gshah@bentley.edu', '7818912908', 'Smith', '4C1', '1', 'https://faculty.bentley.edu/details.asp?uname=gshah', 'https://faculty.bentley.edu/storage/gshah/pci/gaurav-1.jpg'), "+
					"('Ahmed, Akram', 'aahmed@bentley.edu', '7818912713', 'Smith', '302', '1', 'https://faculty.bentley.edu/details.asp?uname=aahmed', 'https://faculty.bentley.edu/storage/aahmed/pci/Akram-Ahmed-1.jpg'), "+
					"('Babaian, Tamara', 'tbabaian@bentley.edu', '7818913161', 'Smith ', '404', '1', 'https://faculty.bentley.edu/details.asp?uname=tbabaian', 'https://faculty.bentley.edu/storage/tbabaian/pci/805349-1.jpg'), "+
					"('Cooprider, Jay', 'jcooprider@bentley.edu', '7818912952', 'Smith', '416', '1', 'https://faculty.bentley.edu/details.asp?uname=jcooprider', 'https://faculty.bentley.edu/storage/jcooprider/pci/467772-1.jpg'), "+
					"('Frydenberg, Mark', 'mfrydenberg@bentley.edu', '7818912689', 'Smith', '414', '1', 'https://faculty.bentley.edu/details.asp?uname=mfrydenberg', 'https://faculty.bentley.edu/storage/mfrydenberg/pci/Mark-Frydenberg-1.jpg'), "+
					"('Garfield, Monica', 'mgarfield@bentley.edu', '7818912910', 'Smith', '409', '1', 'https://faculty.bentley.edu/details.asp?uname=mgarfield', 'https://faculty.bentley.edu/storage/mgarfield/pci/Monica-Garfield-1.jpg'), "+
					"('Lucas, Wendy', 'wlucas@bentley.edu', '7818912554', 'Smith', '407', '1', 'https://faculty.bentley.edu/details.asp?uname=wlucas', 'https://faculty.bentley.edu/storage/wlucas/pci/wlucas-1.jpg'), "+
					"('Pepe, James', 'jpepe@bentley.edu', '7818912736', 'Smith', '406', '1', 'https://faculty.bentley.edu/details.asp?uname=jpepe', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Robertson, Douglas', 'drobertson@bentley.edu', '7818912974', 'Smith', '420', '1', 'https://faculty.bentley.edu/details.asp?uname=drobertson', 'https://faculty.bentley.edu/storage/drobertson/pci/2698-1.jpg'), "+
					"('Schiano, William', 'wschiano@bentley.edu', '7818912555', 'Smith', '418', '1', 'https://faculty.bentley.edu/details.asp?uname=wschiano', 'https://faculty.bentley.edu/storage/wschiano/pci/bill-schiano_193-1.jpg'), "+
					"('Topi, Heikki', 'htopi@bentley.edu', '7818912799', 'Smith', '412', '1', 'https://faculty.bentley.edu/details.asp?uname=htopi', 'https://faculty.bentley.edu/storage/htopi/pci/heikki_topi-1.jpg'), "+
					"('VanderClock, William', 'wvanderclock@bentley.edu', '7818912163', 'Smith', '419', '1', 'https://faculty.bentley.edu/details.asp?uname=wvanderclock', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Waguespack, Leslie', 'lwaguespack@bentley.edu', '7818912584', 'Smith', '415', '1', 'https://faculty.bentley.edu/details.asp?uname=lwaguespack', 'https://faculty.bentley.edu/storage/lwaguespack/pci/IMG_1883-1.jpg'), "+
					"('Wong, Wilson', 'wwong@bentley.edu', '7818912033', 'Smith', '405', '1', 'https://faculty.bentley.edu/details.asp?uname=wwong', 'https://faculty.bentley.edu/storage/wwong/pci/Wilson%20Wong-1.jpg'), "+
					"('Xu, Jie', 'jxu@bentley.edu', '7818912711', 'Smith', '410', '1', 'https://faculty.bentley.edu/details.asp?uname=jxu', 'https://faculty.bentley.edu/storage/jxu/pci/Jennifer-Xu-2010-1.jpg'), "+
					"('Yates, David', 'dyates@bentley.edu', '7818912735', 'Smith', '411', '1', 'https://faculty.bentley.edu/details.asp?uname=dyates', 'https://faculty.bentley.edu/storage/dyates/pci/davidjyates2006revised2-2.jpg'), "+
					"('Bianco, Candy', 'cbianco@bentley.edu', '7818912982', 'Adamian', '270', '3', 'https://faculty.bentley.edu/details.asp?uname=cbianco', 'https://faculty.bentley.edu/storage/cbianco/pci/317426-1.jpg'), "+
					"('Chavez, Gonzalo', 'gchavez@bentley.edu', '7818912109', 'Morison', '186', '3', 'https://faculty.bentley.edu/details.asp?uname=gchavez', 'https://faculty.bentley.edu/storage/gchavez/pci/Gonzalo-Chavez-1.jpg'), "+
					"('Cicchetti, Claude', 'ccicchetti@bentley.edu', '7818912511', 'Morison', '121', '3', 'https://faculty.bentley.edu/details.asp?uname=ccicchetti', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Cornett, Marcia', 'mcornett@bentley.edu', '7818913111', 'Adamian', '227', '3', 'https://faculty.bentley.edu/details.asp?uname=mcornett', 'https://faculty.bentley.edu/storage/mcornett/pci/Marcia-Cornett-1.jpg'), "+
					"('Erhemjamts, Otgontsetseg', 'oerhemjamts@bentley.edu', '7818912823', 'Adamian', '217', '3', 'https://faculty.bentley.edu/details.asp?uname=oerhemjamts', 'https://faculty.bentley.edu/storage/oerhemjamts/pci/Otgo-Erhemjamts-1.jpg'), "+
					"('Fletcher Brown, Donna', 'dfletcher@bentley.edu', '7818912653', 'Morison', '379', '3', 'https://faculty.bentley.edu/details.asp?uname=dfletcher', 'https://faculty.bentley.edu/storage/dfletcher/pci/9565-1.jpg'), "+
					"('Gregory, Deborah', 'dgregory@bentley.edu', '7818912384', 'Adamian', '272', '3', 'https://faculty.bentley.edu/details.asp?uname=dgregory', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Gupta, Atul', 'agupta@bentley.edu', '7818912772', 'Adamian', '215', '3', 'https://faculty.bentley.edu/details.asp?uname=agupta', 'https://faculty.bentley.edu/storage/agupta/pci/Atul%20Gupta%204X3%20Smile-1.JPG'), "+
					"('Hachey, George', 'ghachey@bentley.edu', '7818912514', 'Morison', '113', '3', 'https://faculty.bentley.edu/details.asp?uname=ghachey', 'https://faculty.bentley.edu/storage/ghachey/pci/2290-1.jpg'), "+
					"('Hartman, Mary', 'mhartman@bentley.edu', '7818912081', 'Morison', '175', '3', 'https://faculty.bentley.edu/details.asp?uname=mhartman', 'https://faculty.bentley.edu/storage/mhartman/pci/175522-1.jpg'), "+
					"('Huang, Kershen', 'khuang@bentley.edu', '7818912296', 'Adamian', '213', '3', 'https://faculty.bentley.edu/details.asp?uname=khuang', 'https://faculty.bentley.edu/storage/khuang/pci/FacultyApptScanPortraitFIXDimWebC-1.jpg'), "+
					"('Larkan, Trevor', 'tlarkan@bentley.edu', '7818912219', 'Morison', '230', '3', 'https://faculty.bentley.edu/details.asp?uname=tlarkan', 'https://faculty.bentley.edu/storage/tlarkan/pci/tlarkan%202-1.jpg'), "+
					"('Leabman, Jerry', 'jleabman@bentley.edu', '7818912879', 'Morison', '357', '3', 'https://faculty.bentley.edu/details.asp?uname=jleabman', 'https://faculty.bentley.edu/storage/jleabman/pci/83476-1.jpg'), "+
					"('Milton, David', 'dmilton@bentley.edu', '7818912734', 'Adamian', '229', '3', 'https://faculty.bentley.edu/details.asp?uname=dmilton', 'https://faculty.bentley.edu/storage/dmilton/pci/2559-1.jpg'), "+
					"('Minnick, Kristina', 'kminnick@bentley.edu', '7818912941', 'Adamian', '223', '3', 'https://faculty.bentley.edu/details.asp?uname=kminnick', 'https://faculty.bentley.edu/storage/kminnick/pci/Kristina_Minnick-1.jpg'), "+
					"('Musumeci, James', 'jmusumeci@bentley.edu', '7818912235', 'Morison', '107', '3', 'https://faculty.bentley.edu/details.asp?uname=jmusumeci', 'https://faculty.bentley.edu/storage/jmusumeci/pci/Jim%20Musemeci-1.jpg'), "+
					"('Nelson, David', 'dnelson@bentley.edu', '7818912059', 'Morison', '121', '3', 'https://faculty.bentley.edu/details.asp?uname=dnelson', 'https://faculty.bentley.edu/storage/dnelson/pci/2596-1.jpg'), "+
					"('Page, Michael', 'mpage@bentley.edu', '7818912864', 'Rauch', '310', '3', 'https://faculty.bentley.edu/details.asp?uname=mpage', 'https://faculty.bentley.edu/storage/mpage/pci/mike-page-1.png'), "+
					"('Raman, Kartik', 'kraman@bentley.edu', '7818912781', 'Adamian', '219', '3', 'https://faculty.bentley.edu/details.asp?uname=kraman', 'https://faculty.bentley.edu/storage/kraman/pci/Kartik_Raman-1.jpg'), "+
					"('Reis, Ebru', 'ereis@bentley.edu', '7818912078', 'Adamian', '221', '3', 'https://faculty.bentley.edu/details.asp?uname=ereis', 'https://faculty.bentley.edu/storage/ereis/pci/ebru-reis-1.jpg'), "+
					"('Rosenthal, Leonard', 'lrosenthal@bentley.edu', '7818912516', 'Adamian', '225', '3', 'https://faculty.bentley.edu/details.asp?uname=lrosenthal', 'https://faculty.bentley.edu/storage/lrosenthal/pci/2708-1.jpg'), "+
					"('Simon, David', 'dsimon@bentley.edu', '7818912489', 'Adamian', '275', '3', 'https://faculty.bentley.edu/details.asp?uname=dsimon', 'https://faculty.bentley.edu/storage/dsimon/pci/david-simon-1.jpg'), "+
					"('Siy, Peter', 'psiy@bentley.edu', '7818912570', 'Adamian', '273', '3', 'https://faculty.bentley.edu/details.asp?uname=psiy', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Sultan, Jahangir', 'jsultan@bentley.edu', '7818912518', 'Adamian', '271', '3', 'https://faculty.bentley.edu/details.asp?uname=jsultan', 'https://faculty.bentley.edu/storage/jsultan/pci/jay_sultan-1.jpg'), "+
					"('Uhlmann, Phillip', 'puhlmann@bentley.edu', '7818913175', 'Adamian', '277', '3', 'https://faculty.bentley.edu/details.asp?uname=puhlmann', 'https://faculty.bentley.edu/storage/puhlmann/pci/Phil_Uhlmann-1.jpg'), "+
					"('Wiggins, Roy', 'rwiggins@bentley.edu', '7818913166', 'Morison', '305A', '3', 'https://faculty.bentley.edu/details.asp?uname=rwiggins', 'https://faculty.bentley.edu/storage/rwiggins/pci/roy%20wiggins-1.jpg'), "+
					"('Willet, Laura', 'lwillet@bentley.edu', '7818912152', 'Adamian', '224', '3', 'https://faculty.bentley.edu/details.asp?uname=lwillett', 'https://faculty.bentley.edu/storage/lwillett/pci/765160-1.jpg'), "+
					"('Campasano, Vincent', 'vcampasano@bentley.edu', '7818912982', 'Adamian', '209', '3', 'https://faculty.bentley.edu/details.asp?uname=jcampasano', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Caruso, Frank', 'fcaruso@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=fcaruso', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Chinca, Alain', 'achinca@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=achinca', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Constantino, Raphael', 'rconstantino@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=rconstantino', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Daal, Elton', 'edaal@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=edaal', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Dahan, Arie', 'adahan@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=adahan', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Daroff, Herbert', 'hdaroff@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=hdaroff', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('DeSoye, Caitlin', 'cdesoye@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=cdesoye', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('DiBello, John', 'jdibelio@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=jdibello', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Gibble, Richard', 'rgibble@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=rgibble', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Gregory, Patrick', 'pgregory@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=pgregory', 'https://faculty.bentley.edu/storage/pgregory/pci/545624-1.jpg'), "+
					"('Hanover, Alain', 'ahanover@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=ahanover', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Harrell, Dana', 'dharrell@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=dharrell', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Lanton, Linda', 'llanton@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=llanton', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Leone, Eugene', 'eleone@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=eleone', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Noyes, Richard', 'rnoyes@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=rnoyes', 'https://faculty.bentley.edu/storage/rnoyes/pci/Richard-1.jpg'), "+
					"('Pandit, Anurag', 'apandit@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=apandit', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Pope, David', 'dpope@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=dpope', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Rabinovitz, Stephen', 'srabinovitz@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=srabinovitz', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Read, Karen', 'kread@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=kread', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Rehman, Asif', 'arehman@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=arehman', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Risman, Maksim ', 'mrisman@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=mrisman', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Ritsatos, Titos', 'tritsatos@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=tritsatos', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Schiavo, Michael', 'mschiavo@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=mschiavo', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Sevigny, Kathleen', 'ksevigny@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=ksevigny', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Sgro, Anthony', 'asgro@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=asgro', 'https://faculty.bentley.edu/storage/asgro/pci/Sgro_Business-1.jpg'), "+
					"('Stoller, Linda', 'lstoller@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=lstoller', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Tocci, Joseph', 'jtocci@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=jtocci', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Wasserman, Steven', 'swasserman@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=swasserman', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Willard, Justine', 'jwillard@bentley.edu', '7818912059', 'Adamian', '211', '3', 'https://faculty.bentley.edu/details.asp?uname=jwillard', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Aylesworth, Andrew', 'aaylesworth@bentley.edu', '7818913149', 'Morison', '216', '4', 'https://faculty.bentley.edu/details.asp?uname=aaylesworth', 'https://faculty.bentley.edu/storage/aaylesworth/pci/Andy-Aylesworth-1.jpg'), "+
					"('Berger, Paul', 'pberger@bentley.edu', '7818912746', 'Morison', '351', '4', 'https://faculty.bentley.edu/details.asp?uname=pberger', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Berthon, Pierre', 'pberthon@bentley.edu', '7818913189', 'Morison', '250', '4', 'https://faculty.bentley.edu/details.asp?uname=pberthon', 'https://faculty.bentley.edu/storage/pberthon/pci/PB%20-%20Microsoft%20Talk%202-1.jpg'), "+
					"('Butaney, Gul', 'gbutaney@bentley.edu', '7818912545', 'Morison', '235', '4', 'https://faculty.bentley.edu/details.asp?uname=gbutaney', 'https://faculty.bentley.edu/storage/gbutaney/pci/2027-1.jpg'), "+
					"('Campbell, Leland', 'lcampbell@bentley.edu', '7818913146', 'Morison', '240', '4', 'https://faculty.bentley.edu/details.asp?uname=lcampbell', 'https://faculty.bentley.edu/storage/lcampbell/pci/9568-1.jpg'), "+
					"('Cross, Ian', 'icross@bentley.edu', '7818913188', 'Morison', '238', '4', 'https://faculty.bentley.edu/details.asp?uname=icross', 'https://faculty.bentley.edu/storage/icross/pci/Ian-Cross-1.jpg'), "+
					"('Dobscha, Susan', 'sdobscha@bentley.edu', '7818912042', 'Morison', '275', '4', 'https://faculty.bentley.edu/details.asp?uname=sdobscha', 'https://faculty.bentley.edu/storage/sdobscha/pci/283597-1.jpg'), "+
					"('Eshghi, Abdolreza', 'aeshghi@bentley.edu', '7818912288', 'Morison', '203', '4', 'https://faculty.bentley.edu/details.asp?uname=aeshghi', 'https://faculty.bentley.edu/storage/aeshghi/pci/2198-1.jpg'), "+
					"('Foxman, Ellen', 'efoxman@bentley.edu', '7818912796', 'Morison', '277', '4', 'https://faculty.bentley.edu/details.asp?uname=efoxman', 'https://faculty.bentley.edu/storage/efoxman/pci/9569-1.jpg'), "+
					"('Lowe, Phillip', 'plowe@bentley.edu', '7818913139', 'Morison', '242', '4', 'https://faculty.bentley.edu/details.asp?uname=plowe', 'https://faculty.bentley.edu/storage/plowe/pci/Perry-Lowe-1.jpg'), "+
					"('Moore, Kelvyn', 'kmoore@bentley.edu', '7818912276', 'Morison', '254', '4', 'https://faculty.bentley.edu/details.asp?uname=kmoore', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Nasr, Nada', 'nnasr@bentley.edu', '7818913199', 'Morison', '232', '4', 'https://faculty.bentley.edu/details.asp?uname=kmoore', 'https://faculty.bentley.edu/storage/nnasr/pci/849530-1.jpg'), "+
					"('Pouliopoulos, James', 'jpouliopoulos@bentley.edu', '7818912006', 'Morison', '239', '4', 'https://faculty.bentley.edu/details.asp?uname=jpouliopoulos', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Rowland, Andrew', 'arowland@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=arowland', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Sisodia, Rajendra', 'rsisodia@bentley.edu', '7818913461', 'Morison', '205', '4', 'https://faculty.bentley.edu/details.asp?uname=rsisodia', 'https://faculty.bentley.edu/storage/rsisodia/pci/Raj-Sisodia-1.jpg'), "+
					"('Xia, Lan', 'lxia@bentley.edu', '7818912468', 'Morison', '234', '4', 'https://faculty.bentley.edu/details.asp?uname=lxia', 'https://faculty.bentley.edu/storage/lxia/pci/989165-1.jpg'), "+
					"('Yeohh, Poh-Lin', 'pyeohh@bentley.edu', '7818912261', 'Morison', '276', '4', 'https://faculty.bentley.edu/details.asp?uname=pyeoh', 'https://faculty.bentley.edu/storage/pyeoh/pci/517206-1.jpg'), "+
					"('Benson, Steven', 'sbenson@bentley.edu', '7818913184', 'Morison', '212', '4', 'https://faculty.bentley.edu/details.asp?uname=sbenson', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Bernatchez, Michael', 'mbernatchez@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=mbernatchez', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Catalini, Thomas', 'tcatalini@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=tcatalini', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Collins, Cathleen', 'ccollins@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=ccollins', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Etzenberg, Michael', 'metzenberg@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=meizenberg', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Elwell, Kristin', 'kelwell@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=kelwell', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Giaquinto, John', 'jgiaquinto@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=jgiaquinto', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Gladstone, Peter', 'pgladstone@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=pgladstone', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Hickey, Jonathan', 'jhickey@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=jhickey', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Jones, Gordon', 'gjones@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=gjones', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Kaye, Alyson', 'akaye@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=akaye', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Kinch, Frederick', 'fkinch@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=fkinch', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Landsman, John', 'jlandsman@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=jlandsman', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Pehlivan Yalcin, Ekin', 'pehiva_ekin@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=pehliva_ekin', 'https://faculty.bentley.edu/storage/pehliva_ekin/pci/ekin-1.jpg'), "+
					"('Pellant, Roberta', 'rpellant@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=rpellant', 'https://faculty.bentley.edu/storage/rpellant/pci/bpellant-1.png'), "+
					"('Schroll, Jodi', 'jschroll@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=jschroll', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Simmel, Leslie', 'lsimmel@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=lsimmel', 'https://faculty.bentley.edu/storage/lsimmel/pci/729276-1.jpg'), "+
					"('Snow, Mari', 'msnow@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=msnow', 'https://faculty.bentley.edu/storage/msnow/pci/msnow-1.jpg'), "+
					"('Tesler, Michael', 'mtesler@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=mtesler', 'https://faculty.bentley.edu/storage/mtesler/pci/mike_tesler-1.jpg'), "+
					"('Verma, Dharmendra', 'dverma@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=dverma', 'https://faculty.bentley.edu/images/nopicture.png'), "+
					"('Weinberg, Bruce', 'bweinberg@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=bweinberg', 'https://faculty.bentley.edu/storage/bweinberg/pci/bruce_weinberg_029-1.jpg'), "+
					"('Weisman, David', 'dweisman@bentley.edu', '7818912079', 'Morison', '236', '4', 'https://faculty.bentley.edu/details.asp?uname=dweisman', 'https://faculty.bentley.edu/storage/dweisman/pci/david%20weisman-1.jpg'); ";
	public static final String SQL_DELETE_PROFESSORS = 
			"DROP TABLE IF EXISTS " + DB_Contract.Professor.TABLE_NAME;
	
	/* --------------------
	  	COURSE
	 	-------------------- */
	public static final String SQL_POPULATE_COURSES = 
			"INSERT INTO " + DB_Contract.Course.TABLE_NAME + " ( "+DB_Contract.Course.COLUMN_COURSE_CODE +", "+ DB_Contract.Course.COLUMN_COURSE_DESC +", "+DB_Contract.Course.COLUMN_MAJOR_LINK+") VALUES "+
					"('CS 150', 'Introduction Data & Information Management', '1'), "+
					"('CS 180', 'Programming Fundamentals', '1'), "+
					"('CS 240', 'Business Process & Communication Infrastructure', '1'), "+
					"('CS 350', 'Database Management Systems', '1'), "+
					"('CS 360', 'Business Systems Analysis & Design', '1'), "+
					"('CS Elective x 3', 'Unrestricted CS Elective', '1'), "+
					"('AC 310', 'Cost Management', '2'), "+
					"('AC 311', 'Financial Accounting and Reporting I', '2'), "+
					"('AC 312', 'Financial Accounting and Reporting II', '2'), "+
					"('AC 340', 'Accounting Information Systems', '2'), "+
					"('AC 350', 'Federal Taxation', '2'), "+
					"('AC 412', 'Advanced Accounting', '2'), "+
					"('AC 470', 'Financial Statement Auditing ', '2'), "+
					"('AC 472', 'Internal Auditing', '2'), "+
					"('AC Elective', 'Restricted AC Elective', '2'), "+
					"('FI 305', 'Principles of Accounting and Finance', '3'), "+
					"('FI 320', 'Financial Markets & Investments', '3'), "+
					"('FI 351', 'International Finance', '3'), "+
					"('FI 380', 'Advanced Managerial Finance', '3'), "+
					"('FI Elective x 4', 'Unrestricted FI Elective', '3'), "+
					"('MK 322', 'Marketing Research', '4'), "+
					"('MK 400', 'Marketing Management', '4'), "+
					"('MK Elective x 4', 'Marketing Elective', '4'), "+
					"('?? Elective x 2', 'Marketing-Related Elective', '4'); ";
	public static final String SQL_DELETE_COURSES = 
			"DROP TABLE IF EXISTS " + DB_Contract.Course.TABLE_NAME;	

	
	
	
	/* ----------
		Constructor
	  	---------- */
	public DB_Helper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	@Override
	/* 
	 * Function called on DB Creation
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MAJORS);
		db.execSQL(SQL_POPULATE_MAJORS);
		db.execSQL(SQL_CREATE_DESTINATIONS);
		db.execSQL(SQL_POPULATE_DESTINATIONS);
		db.execSQL(SQL_CREATE_PROFESSORS);
		db.execSQL(SQL_POPULATE_PROFESSORS);
		db.execSQL(SQL_CREATE_ENTRANCES);
		db.execSQL(SQL_POPULATE_ENTRANCES);
		//create courses
		db.execSQL(SQL_CREATE_COURSES);
		//populate courses
		db.execSQL(SQL_POPULATE_COURSES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_DELETE_MAJORS);
		db.execSQL(SQL_DELETE_DESTINATIONS);
		db.execSQL(SQL_DELETE_PROFESSORS);
		db.execSQL(SQL_DELETE_ENTRANCES);
		db.execSQL(SQL_DELETE_COURSES);
		onCreate(db);
		

	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
	
	public SQLiteDatabase getDB(){
		return this.getReadableDatabase();
	}
	
	/**
	 * 
	 * @param x: Table name to query => DB_Contract.Major.TABLE_NAME
	 * @param y: Where clause of SQL query 
	 * 			 	=> DB_Contract.Major.COLUMN_NAME + " = marketing"
	 * @return Cursor object with row from DB. 
	 * 				=> Access with Cursor.getString(Cursor.getColumnIndexOrThrow(
	 */
	public Cursor selectFromXwhereY(String x, String y){
		return getDB().query(
				x, 
				null, 
				y, 
				null, 
				null, 
				null, 
				null, 
				null
			);
	}
	
	
}
