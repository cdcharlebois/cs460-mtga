/**====== ============== ======
 * ------ DB_HELPER.JAVA ------
 * ====== ============== ======
 *  ===	DESCRIPTION ===
 *  => This class creates a readable database and should be called in order to reference the DB.
 * 
 * @author CHARLEB_CONN
 * 	=== MODIFICATIONS ===
 * 	CC	->	03/24/2013 Added functionality to query here [CC]
 * 		-> 	03/26/2013 Added course table
 * 		-> 	04/12/2013 Updated professor table, added more destinations and entrances
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
	public static final int DATABASE_VERSION = 3;	//if you change the schema, you need to update this.
	
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
					"('107','LINDSAY','42.38747','-71.21965'), "+
					"('108','RAUCH','42.388329','-71.221218'), "+
					"('109','LIB_BASEMENT','42.388036','-71.220143'), "+
					"('110','LACAVA','42.388618','-71.219862'), "+
					"('111','LACAVA_BASEMENT','42.388667','-71.220581'), "+
					"('112','LACAVA_CS','42.388618','-71.219862'), "+
					"('113','LIB_Writing_Center','42.38781','-71.22008'), "+
					"('114','Rauch_Financial','42.388701','-71.221026');";
	public static final String SQL_DELETE_ENTRANCES = 
			"DROP TABLE IF EXISTS " + DB_Contract.Entrance.TABLE_NAME;
	//	--------------------
	//		DESTINATION
	//	--------------------
	
public static final String SQL_POPULATE_DESTINATIONS = 
"INSERT INTO " + DB_Contract.Destination.TABLE_NAME + " (" +DB_Contract.Destination.COLUMN_MAJOR +", "+DB_Contract.Destination.COLUMN_LOCATION+", "+ DB_Contract.Destination.COLUMN_NAME +", "+DB_Contract.Destination.COLUMN_ROOM  +", "+DB_Contract.Destination.COLUMN_BLURB  +" ) VALUES"+
		"('1', '105', 'Library', '', ''), "+
		"('1', '107', 'Academic advising', '21', ''), "+
		"('1', '101', 'CIS Department Chair', '415', 'CIS Department Chair, Leslie Waguespack, is located on the fourth floor of Smith. After entering the building, continue left towards the Smith elevator/stairs. After traveling up to the fourth floor, continue straight ahead to room 415. '), "+
		"('1', '101', 'CIS Sandbox', '234', 'The CIS Sandbox is located on the seond floor of Smith. After entering the building, take the stairs down one flight and head left towards the Bentley Trading Room. Once at the Trading Room, continue left to arrive at the CIS Sandbox located in Smith 234.'), "+
		"('2', '105', 'Library', '', ''), "+
		"('2', '107', 'Academic Advising', '21', ''), "+
		"('2', '104', 'Accounting Department Chair', '212', 'Accounting Department Chair, Mark Nixon, is located on the second floor of Adamian. After entering the building, continue to the stairs located on the right side of the lobby. Once at the second floor, continue straight about 50 ft to the Accounting Department located on the left. Mark Nixon''s office is located in room 212'), "+
		"('2', '102', 'ACELAB', '300', 'The ACELAB is located on the third floor of Jennison. After entering the building, turn left towards the Jennison elevator and continue straight to the ACELAB located in Jennison 300.'), "+
		"('3', '105', 'Library', '', ''), "+
		"('3', '107', 'Academic Advising', '21', ''), "+
		"('3', '104', 'Finance Department Chair', '215', 'Finance Department Chair, Atul Gupta, is located on the second floor of Adamian. After entering the building, continue to the stairs located on the right side of the lobby. Once at the second floor, continue straight about 50ft to the Finance Department located on the right. Atul Gupta''s office is located in room 215'), "+
		"('3', '101', 'Trading Room', '226', 'The Bentley Trading Room is located on the second floor of Smith. After entering the building, take the stairs down one flight and turn left to arrive at the Bentley Trading Room. '), "+
		"('4', '105', 'Library', '', ''), "+
		"('4', '107', 'Academic Advising', '21', ''), "+
		"('4', '106', 'Marketing Department Chair', '216', 'Marketing Department Chair, Andrew Aylesworth, is located on the second floor of Morison. After entering the building, continue straight, traveling past the stairs, to arrive at Morison 216'), "+
		"('4', '106', 'CMT', '220', 'The CMT is located on the second floor of Morison. After entering the building, continue straight to arrive at the CMT located on the right.'), "+
		"('1', '108', 'Registrar', '111', 'The Registrar''s office is located at Rauch 111. After entering the building, the registrar''s office is on your immediate right'), "+
		"('1', '109', 'Helpdesk', '41', 'The Bentley University Help Desk is located on the ground floor of the library. After entering the building, the Help Desk will be on your immediate left.'), "+
		"('1', '110', 'EDR', '', 'The Executive Dining Room is located on the thrid floor of Lacava. After entering the building, take the stairs at your immediate right up one flight to the EDR'), "+
		"('1', '111', 'Lower Café', '', ''), "+
		"('1', '112', 'Career Services', '225', 'Career Services is located on the second floor of Lacava. After entering the building, continue straight ahead about 75 feet to Career Services located at Lacava 225'), "+
		"('1', '113', 'Writing Center', '11', 'The Bentley Writing Centeris located on the ground floor of the library. After entering the building continue straight through Einstein''s and the library to arrive at the staircase. After taking the stairs down, the writing center is at your immediate right'), "+
		"('1', '114', 'Financial Services', '104', 'Financial Services is located at Rauch 104. After entering the building continue straight ahead about 30 feet to arrive at Financial Services on your right. '), "+
		"('2', '108', 'Registrar', '111', 'The Registrar''s office is located at Rauch 111. After entering the building, the registrar''s office is on your immediate right'), "+
		"('2', '109', 'Helpdesk', '41', 'The Bentley University Help Desk is located on the ground floor of the library. After entering the building, the Help Desk will be on your immediate left.'), "+
		"('2', '110', 'EDR', '', 'The Executive Dining Room is located on the thrid floor of Lacava. After entering the building, take the stairs at your immediate right up one flight to the EDR'), "+
		"('2', '111', 'Lower Café', '', ''), "+
		"('2', '112', 'Career Services', '225', 'Career Services is located on the second floor of Lacava. After entering the building, continue straight ahead about 75 feet to Career Services located at Lacava 225'), "+
		"('2', '113', 'Writing Center', '11', 'The Bentley Writing Centeris located on the ground floor of the library. After entering the building continue straight through Einstein''s and the library to arrive at the staircase. After taking the stairs down, the writing center is at your immediate right'), "+
		"('2', '114', 'Financial Services', '104', 'Financial Services is located at Rauch 104. After entering the building continue straight ahead about 30 feet to arrive at Financial Services on your right. '), "+
		"('3', '108', 'Registrar', '111', 'The Registrar''s office is located at Rauch 111. After entering the building, the registrar''s office is on your immediate right'), "+
		"('3', '109', 'Helpdesk', '41', 'The Bentley University Help Desk is located on the ground floor of the library. After entering the building, the Help Desk will be on your immediate left.'), "+
		"('3', '110', 'EDR', '', 'The Executive Dining Room is located on the thrid floor of Lacava. After entering the building, take the stairs at your immediate right up one flight to the EDR'), "+
		"('3', '111', 'Lower Café', '', ''), "+
		"('3', '112', 'Career Services', '225', 'Career Services is located on the second floor of Lacava. After entering the building, continue straight ahead about 75 feet to Career Services located at Lacava 225'), "+
		"('3', '113', 'Writing Center', '11', 'The Bentley Writing Centeris located on the ground floor of the library. After entering the building continue straight through Einstein''s and the library to arrive at the staircase. After taking the stairs down, the writing center is at your immediate right'), "+
		"('3', '114', 'Financial Services', '104', 'Financial Services is located at Rauch 104. After entering the building continue straight ahead about 30 feet to arrive at Financial Services on your right. '), "+
		"('4', '108', 'Registrar', '111', 'The Registrar''s office is located at Rauch 111. After entering the building, the registrar''s office is on your immediate right'), "+
		"('4', '109', 'Helpdesk', '41', 'The Bentley University Help Desk is located on the ground floor of the library. After entering the building, the Help Desk will be on your immediate left.'), "+
		"('4', '110', 'EDR', '', 'The Executive Dining Room is located on the thrid floor of Lacava. After entering the building, take the stairs at your immediate right up one flight to the EDR'), "+
		"('4', '111', 'Lower Café', '', ''), "+
		"('4', '112', 'Career Services', '225', 'Career Services is located on the second floor of Lacava. After entering the building, continue straight ahead about 75 feet to Career Services located at Lacava 225'), "+
		"('4', '113', 'Writing Center', '11', 'The Bentley Writing Centeris located on the ground floor of the library. After entering the building continue straight through Einstein''s and the library to arrive at the staircase. After taking the stairs down, the writing center is at your immediate right'), "+
		"('4', '114', 'Financial Services', '104', 'Financial Services is located at Rauch 104. After entering the building continue straight ahead about 30 feet to arrive at Financial Services on your right. ');";

	public static final String SQL_DELETE_DESTINATIONS = 
			"DROP TABLE IF EXISTS " + DB_Contract.Destination.TABLE_NAME;
			
	//	--------------------
	//		PROFESSOR
	//	--------------------
	public static final String SQL_POPULATE_PROFESSORS = 
			"INSERT INTO " + DB_Contract.Professor.TABLE_NAME + " ( "+ DB_Contract.Professor.COLUMN_NAME +", " + DB_Contract.Professor.COLUMN_EMAIL +", " + DB_Contract.Professor.COLUMN_PHONE +", " + DB_Contract.Professor.COLUMN_BUILDING +", " + DB_Contract.Professor.COLUMN_ROOM +", " + DB_Contract.Professor.COLUMN_DEPARTMENT +", " + DB_Contract.Professor.COLUMN_LINK +", " + DB_Contract.Professor.COLUMN_PICTURE +") VALUES "+
					"('Abbott, Melinda','mabbot@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=mabbott','P0001'),"+
					"('Amaral, Alicia','aamaral@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=aamaral','P0001'),"+
					"('Berube, Scott','sberube@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=sberube','P0001'),"+
					"('Beveridge, John','jbeveridge@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=jbeveridge','P0001'),"+
					"('Boulanger, David','tboulanger@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=dboulanger','P0001'),"+
					"('Buttacavoli, Thomas','tbuttacavoli@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=tbuttacavoli','P0001'),"+
					"('Cannon, Nathan','ncannon@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=ncannon','P0001'),"+
					"('Dugar, Amitabh','adugar@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=adugar','P0001'),"+
					"('Evans, Susan','sevans@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=sevans','P0001'),"+
					"('Grenier, Michael','mgrenier@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=mgreiner','P0001'),"+
					"('Gupta, Krishan','kgupta@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=kgupta','P0001'),"+
					"('Hanes, Denise','dhanes@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=dhanes','P0002'),"+
					"('Hartt, Allen','ahartt@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=hartt_alle','P0003'),"+
					"('Hogan, John','jhogan@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=jlhogan','P0001'),"+
					"('Holderness, Darin','holdern_dar@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=holdern_dari','P0004'),"+
					"('Holmes, Gordon','gholmes@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=gholmes','P0001'),"+
					"('McKinley, John','jmckinley@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=jmckinley','P0001'),"+
					"('Miller, Paul','pmiller@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=pmiller1','P0005'),"+
					"('Nolder, Christine','cnolder@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=cnolder','P0006'),"+
					"('Pepe, Leonard','lpepe@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=lpepe','P0001'),"+
					"('Rowat, Chantal','crowat@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=crowat','P0001'),"+
					"('Seltz, William','wseltz@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=wseltz','P0001'),"+
					"('Spector, Cindy','cspector@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=cspector','P0001'),"+
					"('Speros, Jonathan','jsperos@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=jsperos','P0007'),"+
					"('Spinace-Casale, Antonella','acasale@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=avogel','P0001'),"+
					"('White, James','jwhite@bentley.edu','7818912091','Adamian','280','2','https://faculty.bentley.edu/details.asp?uname=jfwhite','P0001'),"+
					"('Abdolmohammad, Mohammad','mabdolmohammad@bentley.edu','7818912976','Morison','324','2','https://faculty.bentley.edu/details.asp?uname=mabdolmohamm','P0008'),"+
					"('Baxter, Ryan','rbaxter@bentley.edu','7818913485','Adamian','289','2','https://faculty.bentley.edu/details.asp?uname=rbaxter','P0009'),"+
					"('Bedard, Jean','jbedard@bentley.edu','7818912410','Adamian','240','2','https://faculty.bentley.edu/details.asp?uname=jbedard','P0010'),"+
					"('Boss, Scott','sboss@bentley.edu','7818912353','Adamian','267','2','https://faculty.bentley.edu/details.asp?uname=sboss','P0011'),"+
					"('Bumbay, Priscilla','pbumbay@bentley.edu','7818912519','Adamian','214','2','https://faculty.bentley.edu/details.asp?uname=pburnaby','P0012'),"+
					"('Cook, Edward','ecook@bentley.edu','7818912091','Not provided','Not provided','2','https://faculty.bentley.edu/details.asp?uname=ecook','P0001'),"+
					"('Fedorowicz, Jane','jfedorowicz@bentley.edu','7818913153','Adamian','206','2','https://faculty.bentley.edu/details.asp?uname=jfedorowicz','P0013'),"+
					"('Feldman, Dorothy','dfeldman@bentley.edu','7818912782','Morison','305B','2','https://faculty.bentley.edu/details.asp?uname=dfeldmann','P0014'),"+
					"('Fionda, David','dfionda@bentley.edu','7818912091','Not provided','Not provided','2','https://faculty.bentley.edu/details.asp?uname=dfionda','P0001'),"+
					"('Freed, Alan','afreed@bentley.edu','7818912409','Morison','149G','2','https://faculty.bentley.edu/details.asp?uname=afreed','P0015'),"+
					"('Graham, Lynford','lgraham@bentley.edu','7818912091','Not provided','Not provided','2','https://faculty.bentley.edu/details.asp?uname=lgraham','P0016'),"+
					"('Gujarathi, Mahendra','mgujarathi@bentley.edu','7818913408','Adamian','218','2','https://faculty.bentley.edu/details.asp?uname=mgujarathi','P0017'),"+
					"('Haselkom, Michael','mhaskelkom@bentley.edu','7818912521','Adamian','216','2','https://faculty.bentley.edu/details.asp?uname=mhaselkorn','P0018'),"+
					"('Hoitash, Ran','rhoitash@bentley.edu','7818912588','Adamian','279','2','https://faculty.bentley.edu/details.asp?uname=rhoitash','P0019'),"+
					"('Howe, Martha','mhowe@bentley.edu','7818912573','Adamian','220','2','https://faculty.bentley.edu/details.asp?uname=mhowe','P0020'),"+
					"('Kelly, Doris','dkelly@bentley.edu','7818912412','Smith','310','2','https://faculty.bentley.edu/details.asp?uname=dkelly','P0021'),"+
					"('Levesque, Raymond','rlevesque@bentley.edu','7818913486','Morison','378','2','https://faculty.bentley.edu/details.asp?uname=rlevesque','P0022'),"+
					"('Lindblom, Cristi','clindblom@bentley.edu','7818912499','Adamian','210','2','https://faculty.bentley.edu/details.asp?uname=bmaciver','P0001'),"+
					"('MacIver, Brian','bmaciver@bentley.edu','7818912134','Morison','282','2','https://faculty.bentley.edu/details.asp?uname=bmaciver','P0001'),"+
					"('Malgwi, Charles','cmalgwi@bentley.edu','7818912774','Adamian','285','2','https://faculty.bentley.edu/details.asp?uname=cmalgwi','P0023'),"+
					"('McConville, Donna','dmcconville@bentley.edu','7818912433','Morison','149A','2','https://faculty.bentley.edu/details.asp?uname=dmcconville','P0024'),"+
					"('Nixon, Mark','mnixon@bentley.edu','7818912087','Adamian','212','2','https://faculty.bentley.edu/details.asp?uname=mnixon','P0025'),"+
					"('Noga, Tracy','ntracy@bentley.edu','7818912432','Morison','131','2','https://faculty.bentley.edu/details.asp?uname=tnoga','P0026'),"+
					"('Ostherheld, Karen','kostherheld@bentley.edu','7818912724','Adamian','282','2','https://faculty.bentley.edu/details.asp?uname=kosterheld','P0027'),"+
					"('Read, William','wread@bentley.edu','7818912525','Adamian','222','2','https://faculty.bentley.edu/details.asp?uname=wread','P0028'),"+
					"('Reed, Arthur','areed@bentley.edu','7818912323','Morison','126','2','https://faculty.bentley.edu/details.asp?uname=areed','P0029'),"+
					"('Ruff, Michael','mruff@bentley.edu','7818912402','Morison','126','2','https://faculty.bentley.edu/details.asp?uname=mruff','P0030'),"+
					"('Schnader, Anne','aschnader@bentley.edu','7818912439','Morison','119','2','https://faculty.bentley.edu/details.asp?uname=aschnader','P0031'),"+
					"('Schwarzkopf, David','dschwarzkopf@bentley.edu','7818912783','Adamian','283','2','https://faculty.bentley.edu/details.asp?uname=dschwarzkopf','P0032'),"+
					"('Thibodeau, Jay','jthibodeau@bentley.edu','7818912134','Morison','373','2','https://faculty.bentley.edu/details.asp?uname=jthibodeau','P0033'),"+
					"('Willett, Laura','lwillet@bentley.edu','7818912152','Adamian','224','2','https://faculty.bentley.edu/details.asp?uname=lwillett','P0034'),"+
					"('Yezegel, Ari','ayezegel@bentley.edu','7818912264','Adamian','287','2','https://faculty.bentley.edu/details.asp?uname=ayezegel','P0035'),"+
					"('Aucoin, Donald','daucoin@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=daucoin','P0001'),"+
					"('Jones, Andrea','ajones@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=ajones','P0001'),"+
					"('Macarty, Matthew','mmacarty@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=mmacarty','P0001'),"+
					"('McCarron, Elizabeth','emccarron@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=emccarron','P0001'),"+
					"('Nezuh, Joseph','jnezuh@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=jnezuh','P0001'),"+
					"('Rice, Patricia','price@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=price','P0001'),"+
					"('Rude, David','drude@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=drude','P0001'),"+
					"('Shah, Gauray','gshah@bentley.edu','7818912908','Smith','4C1','1','https://faculty.bentley.edu/details.asp?uname=gshah','P0036'),"+
					"('Ahmed, Akram','aahmed@bentley.edu','7818912713','Smith','302','1','https://faculty.bentley.edu/details.asp?uname=aahmed','P0037'),"+
					"('Babaian, Tamara','tbabaian@bentley.edu','7818913161','Smith ','404','1','https://faculty.bentley.edu/details.asp?uname=tbabaian','P0038'),"+
					"('Cooprider, Jay','jcooprider@bentley.edu','7818912952','Smith','416','1','https://faculty.bentley.edu/details.asp?uname=jcooprider','P0039'),"+
					"('Frydenberg, Mark','mfrydenberg@bentley.edu','7818912689','Smith','414','1','https://faculty.bentley.edu/details.asp?uname=mfrydenberg','P0040'),"+
					"('Garfield, Monica','mgarfield@bentley.edu','7818912910','Smith','409','1','https://faculty.bentley.edu/details.asp?uname=mgarfield','P0041'),"+
					"('Lucas, Wendy','wlucas@bentley.edu','7818912554','Smith','407','1','https://faculty.bentley.edu/details.asp?uname=wlucas','P0042'),"+
					"('Pepe, James','jpepe@bentley.edu','7818912736','Smith','406','1','https://faculty.bentley.edu/details.asp?uname=jpepe','P0001'),"+
					"('Robertson, Douglas','drobertson@bentley.edu','7818912974','Smith','420','1','https://faculty.bentley.edu/details.asp?uname=drobertson','P0043'),"+
					"('Schiano, William','wschiano@bentley.edu','7818912555','Smith','418','1','https://faculty.bentley.edu/details.asp?uname=wschiano','P0044'),"+
					"('Topi, Heikki','htopi@bentley.edu','7818912799','Smith','412','1','https://faculty.bentley.edu/details.asp?uname=htopi','P0045'),"+
					"('VanderClock, William','wvanderclock@bentley.edu','7818912163','Smith','419','1','https://faculty.bentley.edu/details.asp?uname=wvanderclock','P0001'),"+
					"('Waguespack, Leslie','lwaguespack@bentley.edu','7818912584','Smith','415','1','https://faculty.bentley.edu/details.asp?uname=lwaguespack','P0046'),"+
					"('Wong, Wilson','wwong@bentley.edu','7818912033','Smith','405','1','https://faculty.bentley.edu/details.asp?uname=wwong','P0047'),"+
					"('Xu, Jie','jxu@bentley.edu','7818912711','Smith','410','1','https://faculty.bentley.edu/details.asp?uname=jxu','P0048'),"+
					"('Yates, David','dyates@bentley.edu','7818912735','Smith','411','1','https://faculty.bentley.edu/details.asp?uname=dyates','P0049'),"+
					"('Bianco, Candy','cbianco@bentley.edu','7818912982','Adamian','270','3','https://faculty.bentley.edu/details.asp?uname=cbianco','P0050'),"+
					"('Chavez, Gonzalo','gchavez@bentley.edu','7818912109','Morison','186','3','https://faculty.bentley.edu/details.asp?uname=gchavez','P0051'),"+
					"('Cicchetti, Claude','ccicchetti@bentley.edu','7818912511','Morison','121','3','https://faculty.bentley.edu/details.asp?uname=ccicchetti','P0001'),"+
					"('Cornett, Marcia','mcornett@bentley.edu','7818913111','Adamian','227','3','https://faculty.bentley.edu/details.asp?uname=mcornett','P0052'),"+
					"('Erhemjamts, Otgontsetseg','oerhemjamts@bentley.edu','7818912823','Adamian','217','3','https://faculty.bentley.edu/details.asp?uname=oerhemjamts','P0053'),"+
					"('Fletcher Brown, Donna','dfletcher@bentley.edu','7818912653','Morison','379','3','https://faculty.bentley.edu/details.asp?uname=dfletcher','P0054'),"+
					"('Gregory, Deborah','dgregory@bentley.edu','7818912384','Adamian','272','3','https://faculty.bentley.edu/details.asp?uname=dgregory','P0001'),"+
					"('Gupta, Atul','agupta@bentley.edu','7818912772','Adamian','215','3','https://faculty.bentley.edu/details.asp?uname=agupta','P0055'),"+
					"('Hachey, George','ghachey@bentley.edu','7818912514','Morison','113','3','https://faculty.bentley.edu/details.asp?uname=ghachey','P0056'),"+
					"('Hartman, Mary','mhartman@bentley.edu','7818912081','Morison','175','3','https://faculty.bentley.edu/details.asp?uname=mhartman','P0057'),"+
					"('Huang, Kershen','khuang@bentley.edu','7818912296','Adamian','213','3','https://faculty.bentley.edu/details.asp?uname=khuang','P0058'),"+
					"('Larkan, Trevor','tlarkan@bentley.edu','7818912219','Morison','230','3','https://faculty.bentley.edu/details.asp?uname=tlarkan','P0059'),"+
					"('Leabman, Jerry','jleabman@bentley.edu','7818912879','Morison','357','3','https://faculty.bentley.edu/details.asp?uname=jleabman','P0060'),"+
					"('Milton, David','dmilton@bentley.edu','7818912734','Adamian','229','3','https://faculty.bentley.edu/details.asp?uname=dmilton','P0061'),"+
					"('Minnick, Kristina','kminnick@bentley.edu','7818912941','Adamian','223','3','https://faculty.bentley.edu/details.asp?uname=kminnick','P0062'),"+
					"('Musumeci, James','jmusumeci@bentley.edu','7818912235','Morison','107','3','https://faculty.bentley.edu/details.asp?uname=jmusumeci','P0063'),"+
					"('Nelson, David','dnelson@bentley.edu','7818912059','Morison','121','3','https://faculty.bentley.edu/details.asp?uname=dnelson','P0064'),"+
					"('Page, Michael','mpage@bentley.edu','7818912864','Rauch','310','3','https://faculty.bentley.edu/details.asp?uname=mpage','P0065'),"+
					"('Raman, Kartik','kraman@bentley.edu','7818912781','Adamian','219','3','https://faculty.bentley.edu/details.asp?uname=kraman','P0066'),"+
					"('Reis, Ebru','ereis@bentley.edu','7818912078','Adamian','221','3','https://faculty.bentley.edu/details.asp?uname=ereis','P0067'),"+
					"('Rosenthal, Leonard','lrosenthal@bentley.edu','7818912516','Adamian','225','3','https://faculty.bentley.edu/details.asp?uname=lrosenthal','P0068'),"+
					"('Simon, David','dsimon@bentley.edu','7818912489','Adamian','275','3','https://faculty.bentley.edu/details.asp?uname=dsimon','P0069'),"+
					"('Siy, Peter','psiy@bentley.edu','7818912570','Adamian','273','3','https://faculty.bentley.edu/details.asp?uname=psiy','P0001'),"+
					"('Sultan, Jahangir','jsultan@bentley.edu','7818912518','Adamian','271','3','https://faculty.bentley.edu/details.asp?uname=jsultan','P0070'),"+
					"('Uhlmann, Phillip','puhlmann@bentley.edu','7818913175','Adamian','277','3','https://faculty.bentley.edu/details.asp?uname=puhlmann','P0071'),"+
					"('Wiggins, Roy','rwiggins@bentley.edu','7818913166','Morison','305A','3','https://faculty.bentley.edu/details.asp?uname=rwiggins','P0072'),"+
					"('Willet, Laura','lwillet@bentley.edu','7818912152','Adamian','224','3','https://faculty.bentley.edu/details.asp?uname=lwillett','P0073'),"+
					"('Campasano, Vincent','vcampasano@bentley.edu','7818912982','Adamian','209','3','https://faculty.bentley.edu/details.asp?uname=jcampasano','P0001'),"+
					"('Caruso, Frank','fcaruso@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=fcaruso','P0001'),"+
					"('Chinca, Alain','achinca@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=achinca','P0001'),"+
					"('Constantino, Raphael','rconstantino@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=rconstantino','P0001'),"+
					"('Daal, Elton','edaal@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=edaal','P0001'),"+
					"('Dahan, Arie','adahan@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=adahan','P0001'),"+
					"('Daroff, Herbert','hdaroff@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=hdaroff','P0001'),"+
					"('DeSoye, Caitlin','cdesoye@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=cdesoye','P0001'),"+
					"('DiBello, John','jdibelio@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=jdibello','P0001'),"+
					"('Gibble, Richard','rgibble@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=rgibble','P0001'),"+
					"('Gregory, Patrick','pgregory@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=pgregory','P0074'),"+
					"('Hanover, Alain','ahanover@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=ahanover','P0001'),"+
					"('Harrell, Dana','dharrell@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=dharrell','P0001'),"+
					"('Lanton, Linda','llanton@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=llanton','P0001'),"+
					"('Leone, Eugene','eleone@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=eleone','P0001'),"+
					"('Noyes, Richard','rnoyes@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=rnoyes','P0075'),"+
					"('Pandit, Anurag','apandit@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=apandit','P0001'),"+
					"('Pope, David','dpope@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=dpope','P0001'),"+
					"('Rabinovitz, Stephen','srabinovitz@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=srabinovitz','P0001'),"+
					"('Read, Karen','kread@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=kread','P0001'),"+
					"('Rehman, Asif','arehman@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=arehman','P0001'),"+
					"('Risman, Maksim ','mrisman@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=mrisman','P0001'),"+
					"('Ritsatos, Titos','tritsatos@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=tritsatos','P0001'),"+
					"('Schiavo, Michael','mschiavo@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=mschiavo','P0001'),"+
					"('Sevigny, Kathleen','ksevigny@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=ksevigny','P0001'),"+
					"('Sgro, Anthony','asgro@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=asgro','P0076'),"+
					"('Stoller, Linda','lstoller@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=lstoller','P0001'),"+
					"('Tocci, Joseph','jtocci@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=jtocci','P0001'),"+
					"('Wasserman, Steven','swasserman@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=swasserman','P0001'),"+
					"('Willard, Justine','jwillard@bentley.edu','7818912059','Adamian','211','3','https://faculty.bentley.edu/details.asp?uname=jwillard','P0001'),"+
					"('Aylesworth, Andrew','aaylesworth@bentley.edu','7818913149','Morison','216','4','https://faculty.bentley.edu/details.asp?uname=aaylesworth','P0077'),"+
					"('Berger, Paul','pberger@bentley.edu','7818912746','Morison','351','4','https://faculty.bentley.edu/details.asp?uname=pberger','P0001'),"+
					"('Berthon, Pierre','pberthon@bentley.edu','7818913189','Morison','250','4','https://faculty.bentley.edu/details.asp?uname=pberthon','P0078'),"+
					"('Butaney, Gul','gbutaney@bentley.edu','7818912545','Morison','235','4','https://faculty.bentley.edu/details.asp?uname=gbutaney','P0079'),"+
					"('Campbell, Leland','lcampbell@bentley.edu','7818913146','Morison','240','4','https://faculty.bentley.edu/details.asp?uname=lcampbell','P0080'),"+
					"('Cross, Ian','icross@bentley.edu','7818913188','Morison','238','4','https://faculty.bentley.edu/details.asp?uname=icross','P0081'),"+
					"('Dobscha, Susan','sdobscha@bentley.edu','7818912042','Morison','275','4','https://faculty.bentley.edu/details.asp?uname=sdobscha','P0082'),"+
					"('Eshghi, Abdolreza','aeshghi@bentley.edu','7818912288','Morison','203','4','https://faculty.bentley.edu/details.asp?uname=aeshghi','P0083'),"+
					"('Foxman, Ellen','efoxman@bentley.edu','7818912796','Morison','277','4','https://faculty.bentley.edu/details.asp?uname=efoxman','P0084'),"+
					"('Lowe, Phillip','plowe@bentley.edu','7818913139','Morison','242','4','https://faculty.bentley.edu/details.asp?uname=plowe','P0085'),"+
					"('Moore, Kelvyn','kmoore@bentley.edu','7818912276','Morison','254','4','https://faculty.bentley.edu/details.asp?uname=kmoore','P0001'),"+
					"('Nasr, Nada','nnasr@bentley.edu','7818913199','Morison','232','4','https://faculty.bentley.edu/details.asp?uname=kmoore','P0086'),"+
					"('Pouliopoulos, James','jpouliopoulos@bentley.edu','7818912006','Morison','239','4','https://faculty.bentley.edu/details.asp?uname=jpouliopoulos','P0001'),"+
					"('Rowland, Andrew','arowland@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=arowland','P0001'),"+
					"('Sisodia, Rajendra','rsisodia@bentley.edu','7818913461','Morison','205','4','https://faculty.bentley.edu/details.asp?uname=rsisodia','P0087'),"+
					"('Xia, Lan','lxia@bentley.edu','7818912468','Morison','234','4','https://faculty.bentley.edu/details.asp?uname=lxia','P0088'),"+
					"('Yeohh, Poh-Lin','pyeohh@bentley.edu','7818912261','Morison','276','4','https://faculty.bentley.edu/details.asp?uname=pyeoh','P0089'),"+
					"('Benson, Steven','sbenson@bentley.edu','7818913184','Morison','212','4','https://faculty.bentley.edu/details.asp?uname=sbenson','P0001'),"+
					"('Bernatchez, Michael','mbernatchez@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=mbernatchez','P0001'),"+
					"('Catalini, Thomas','tcatalini@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=tcatalini','P0001'),"+
					"('Collins, Cathleen','ccollins@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=ccollins','P0001'),"+
					"('Etzenberg, Michael','metzenberg@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=meizenberg','P0001'),"+
					"('Elwell, Kristin','kelwell@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=kelwell','P0001'),"+
					"('Giaquinto, John','jgiaquinto@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=jgiaquinto','P0001'),"+
					"('Gladstone, Peter','pgladstone@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=pgladstone','P0001'),"+
					"('Hickey, Jonathan','jhickey@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=jhickey','P0001'),"+
					"('Jones, Gordon','gjones@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=gjones','P0001'),"+
					"('Kaye, Alyson','akaye@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=akaye','P0001'),"+
					"('Kinch, Frederick','fkinch@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=fkinch','P0001'),"+
					"('Landsman, John','jlandsman@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=jlandsman','P0001'),"+
					"('Pehlivan Yalcin, Ekin','pehiva_ekin@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=pehliva_ekin','P0090'),"+
					"('Pellant, Roberta','rpellant@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=rpellant','P0091'),"+
					"('Schroll, Jodi','jschroll@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=jschroll','P0001'),"+
					"('Simmel, Leslie','lsimmel@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=lsimmel','P0092'),"+
					"('Snow, Mari','msnow@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=msnow','P0093'),"+
					"('Tesler, Michael','mtesler@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=mtesler','P0094'),"+
					"('Verma, Dharmendra','dverma@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=dverma','P0001'),"+
					"('Weinberg, Bruce','bweinberg@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=bweinberg','P0095'),"+
					"('Weisman, David','dweisman@bentley.edu','7818912079','Morison','236','4','https://faculty.bentley.edu/details.asp?uname=dweisman','P0096');";

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
