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
			DB_Contract.Destination.COLUMN_ROOM + TEXT_TYPE + NULL +			
			") ";
	public static final String SQL_CREATE_PROFESSORS = 
			"CREATE TABLE " + DB_Contract.Professor.TABLE_NAME + " ( " +
			DB_Contract.Professor._ID + " INTEGER PRIMARY KEY, "+ 
			DB_Contract.Professor.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP + 
			DB_Contract.Professor.COLUMN_PHONE + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_BUILDING + TEXT_TYPE + COMMA_SEP + 
			DB_Contract.Professor.COLUMN_ROOM + TEXT_TYPE + COMMA_SEP +
			DB_Contract.Professor.COLUMN_DEPARTMENT + TEXT_TYPE +
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
			"INSERT INTO " + DB_Contract.Destination.TABLE_NAME + "(" + DB_Contract.Destination.COLUMN_MAJOR +", "+DB_Contract.Destination.COLUMN_LOCATION+", "+ DB_Contract.Destination.COLUMN_NAME +", "+DB_Contract.Destination.COLUMN_ROOM  +") VALUES"+
			"('1', '105', 'Library', ''), "+
			"('1', '107', 'Academic advising', '21'), "+
			"('1', '101', 'CIS Department Chair', '420'), "+
			"('1', '101', 'CIS Sandbox', '234'), "+
			"('2', '105', 'Library', ''), "+
			"('2', '107', 'Academic Advising', '21'), "+
			"('2', '104', 'Accounting Department Chair', '212'), "+
			"('2', '102', 'ACELAB', '300'), "+
			"('3', '105', 'Library', ''), "+
			"('3', '107', 'Academic Advising', '21'), "+
			"('3', '104', 'Finance Department Chair', '215'), "+
			"('3', '101', 'Trading Room', '226'), "+
			"('4', '105', 'Library', ''), "+
			"('4', '107', 'Academic Advising', '21'), "+
			"('4', '106', 'Marketing Department Chair', '216'), "+
			"('4', '106', 'CMT', '220'); ";
	public static final String SQL_DELETE_DESTINATIONS = 
			"DROP TABLE IF EXISTS " + DB_Contract.Destination.TABLE_NAME;
			
	//	--------------------
	//		PROFESSOR
	//	--------------------
	public static final String SQL_POPULATE_PROFESSORS = 
			"INSERT INTO " + DB_Contract.Professor.TABLE_NAME + " ( "+ DB_Contract.Professor.COLUMN_NAME +", " + DB_Contract.Professor.COLUMN_EMAIL +", " + DB_Contract.Professor.COLUMN_PHONE +", " + DB_Contract.Professor.COLUMN_BUILDING +", " + DB_Contract.Professor.COLUMN_ROOM +", " + DB_Contract.Professor.COLUMN_DEPARTMENT +") VALUES "+
					"('Abbott, Melinda', 'mabbot@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Amaral, Alicia', 'aamaral@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Berube, Scott', 'sberube@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Beveridge, John', 'jbeveridge@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Boulanger, David', 'tboulanger@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Buttacavoli, Thomas', 'tbuttacavoli@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Cannon, Nathan', 'ncannon@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Dugar, Amitabh', 'adugar@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Evans, Susan', 'sevans@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Grenier, Michael', 'mgrenier@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Gupta, Krishan', 'kgupta@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Hanes, Denise', 'dhanes@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Hartt, Allen', 'ahartt@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Hogan, John', 'jhogan@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Holderness, Darin', 'holdern_dar@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Holmes, Gordon', 'gholmes@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('McKinley, John', 'jmckinley@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Miller, Paul', 'pmiller@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Nolder, Christine', 'cnolder@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Pepe, Leonard', 'lpepe@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Rowat, Chantal', 'crowat@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Seltz, William', 'wseltz@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Spector, Cindy', 'cspector@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Speros, Jonathan', 'jsperos@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Spinace-Casale, Antonella', 'acasale@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('White, James', 'jwhite@bentley.edu', '7818912091', 'Adamian', '280', 'Accountancy'),"+
					"('Abdolmohammad, Mohammad', 'mabdolmohammad@bentley.edu', '7818912976', 'Morison', '324', 'Accountancy'),"+
					"('Baxter, Ryan', 'rbaxter@bentley.edu', '7818913485', 'Adamian', '289', 'Accountancy'),"+
					"('Bedard, Jean', 'jbedard@bentley.edu', '7818912410', 'Adamian', '240', 'Accountancy'),"+
					"('Boss, Scott', 'sboss@bentley.edu', '7818912353', 'Adamian', '267', 'Accountancy'),"+
					"('Bumbay, Priscilla', 'pbumbay@bentley.edu', '7818912519', 'Adamian', '214', 'Accountancy'),"+
					"('Cook, Edward', 'ecook@bentley.edu', '7818912091', 'Not provided', 'Not provided', 'Accountancy'),"+
					"('Fedorowicz, Jane', 'jfedorowicz@bentley.edu', '7818913153', 'Adamian', '206', 'Accountancy'),"+
					"('Feldman, Dorothy', 'dfeldman@bentley.edu', '7818912782', 'Morison', '305B', 'Accountancy'),"+
					"('Fionda, David', 'dfionda@bentley.edu', '7818912091', 'Not provided', 'Not provided', 'Accountancy'),"+
					"('Freed, Alan', 'afreed@bentley.edu', '7818912409', 'Morison', '149G', 'Accountancy'),"+
					"('Graham, Lynford', 'lgraham@bentley.edu', '7818912091', 'Not provided', 'Not provided', 'Accountancy'),"+
					"('Gujarathi, Mahendra', 'mgujarathi@bentley.edu', '7818913408', 'Adamian', '218', 'Accountancy'),"+
					"('Haselkom, Michael', 'mhaskelkom@bentley.edu', '7818912521', 'Adamian', '216', 'Accountancy'),"+
					"('Hoitash, Ran', 'rhoitash@bentley.edu', '7818912588', 'Adamian', '279', 'Accountancy'),"+
					"('Howe, Martha', 'mhowe@bentley.edu', '7818912573', 'Adamian', '220', 'Accountancy'),"+
					"('Kelly, Doris', 'dkelly@bentley.edu', '7818912412', 'Smith', '310', 'Accountancy'),"+
					"('Levesque, Raymond', 'rlevesque@bentley.edu', '7818913486', 'Morison', '378', 'Accountancy'),"+
					"('Lindblom, Cristi', 'clindblom@bentley.edu', '7818912499', 'Adamian', '210', 'Accountancy'),"+
					"('MacIver, Brian', 'bmaciver@bentley.edu', '7818912134', 'Morison', '282', 'Accountancy'),"+
					"('Malgwi, Charles', 'cmalgwi@bentley.edu', '7818912774', 'Adamian', '285', 'Accountancy'),"+
					"('McConville, Donna', 'dmcconville@bentley.edu', '7818912433', 'Morison', '149A', 'Accountancy'),"+
					"('Nixon, Mark', 'mnixon@bentley.edu', '7818912087', 'Adamian', '212', 'Accountancy'),"+
					"('Noga, Tracy', 'ntracy@bentley.edu', '7818912432', 'Morison', '131', 'Accountancy'),"+
					"('Ostherheld, Karen', 'kostherheld@bentley.edu', '7818912724', 'Adamian', '282', 'Accountancy'),"+
					"('Read, William', 'wread@bentley.edu', '7818912525', 'Adamian', '222', 'Accountancy'),"+
					"('Reed, Arthur', 'areed@bentley.edu', '7818912323', 'Morison', '126', 'Accountancy'),"+
					"('Ruff, Michael', 'mruff@bentley.edu', '7818912402', 'Morison', '126', 'Accountancy'),"+
					"('Schnader, Anne', 'aschnader@bentley.edu', '7818912439', 'Morison', '119', 'Accountancy'),"+
					"('Schwarzkopf, David', 'dschwarzkopf@bentley.edu', '7818912783', 'Adamian', '283', 'Accountancy'),"+
					"('Thibodeau, Jay', 'jthibodeau@bentley.edu', '7818912134', 'Morison', '373', 'Accountancy'),"+
					"('Willett, Laura', 'lwillet@bentley.edu', '7818912152', 'Adamian', '224', 'Accountancy'),"+
					"('Yezegel, Ari', 'ayezegel@bentley.edu', '7818912264', 'Adamian', '287', 'Accountancy'),"+
					"('Aucoin, Donald', 'daucoin@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Jones, Andrea', 'ajones@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Macarty, Matthew', 'mmacarty@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('McCarron, Elizabeth', 'emccarron@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Nezuh, Joseph', 'jnezuh@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Rice, Patricia', 'price@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Rude, David', 'drude@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Shah, Gauray', 'gshah@bentley.edu', '7818912908', 'Smith', '4C1', 'CIS'),"+
					"('Ahmed, Akram', 'aahmed@bentley.edu', '7818912713', 'Smith', '302', 'CIS'),"+
					"('Babaian, Tamara', 'tbabaian@bentley.edu', '7818913161', 'Smith ', '404', 'CIS'),"+
					"('Cooprider, Jay', 'jcooprider@bentley.edu', '7818912952', 'Smith', '416', 'CIS'),"+
					"('Frydenberg, Mark', 'mfrydenberg@bentley.edu', '7818912689', 'Smith', '414', 'CIS'),"+
					"('Garfield, Monica', 'mgarfield@bentley.edu', '7818912910', 'Smith', '409', 'CIS'),"+
					"('Lucas, Wendy', 'wlucas@bentley.edu', '7818912554', 'Smith', '407', 'CIS'),"+
					"('Pepe, James', 'jpepe@bentley.edu', '7818912736', 'Smith', '406', 'CIS'),"+
					"('Robertson, Douglas', 'drobertson@bentley.edu', '7818912974', 'Smith', '420', 'CIS'),"+
					"('Schiano, William', 'wschiano@bentley.edu', '7818912555', 'Smith', '418', 'CIS'),"+
					"('Topi, Heikki', 'htopi@bentley.edu', '7818912799', 'Smith', '412', 'CIS'),"+
					"('VanderClock, William', 'wvanderclock@bentley.edu', '7818912163', 'Smith', '419', 'CIS'),"+
					"('Waguespack, Leslie', 'lwaguespack@bentley.edu', '7818912584', 'Smith', '415', 'CIS'),"+
					"('Wong, Wilson', 'wwong@bentley.edu', '7818912033', 'Smith', '405', 'CIS'),"+
					"('Xu, Jie', 'jxu@bentley.edu', '7818912711', 'Smith', '410', 'CIS'),"+
					"('Yates, David', 'dyates@bentley.edu', '7818912735', 'Smith', '411', 'CIS'),"+
					"('Bianco, Candy', 'cbianco@bentley.edu', '7818912982', 'Adamian', '270', 'Finance'),"+
					"('Chavez, Gonzalo', 'gchavez@bentley.edu', '7818912109', 'Morison', '186', 'Finance'),"+
					"('Cicchetti, Claude', 'ccicchetti@bentley.edu', '7818912511', 'Morison', '121', 'Finance'),"+
					"('Cornett, Marcia', 'mcornett@bentley.edu', '7818913111', 'Adamian', '227', 'Finance'),"+
					"('Erhemjamts, Otgontsetseg', 'oerhemjamts@bentley.edu', '7818912823', 'Adamian', '217', 'Finance'),"+
					"('Fletcher Brown, Donna', 'dfletcher@bentley.edu', '7818912653', 'Morison', '379', 'Finance'),"+
					"('Gregory, Deborah', 'dgregory@bentley.edu', '7818912384', 'Adamian', '272', 'Finance'),"+
					"('Gupta, Atul', 'agupta@bentley.edu', '7818912772', 'Adamian', '215', 'Finance'),"+
					"('Hachey, George', 'ghachey@bentley.edu', '7818912514', 'Morison', '113', 'Finance'),"+
					"('Hartman, Mary', 'mhartman@bentley.edu', '7818912081', 'Morison', '175', 'Finance'),"+
					"('Huang, Kershen', 'khuang@bentley.edu', '7818912296', 'Adamian', '213', 'Finance'),"+
					"('Larkan, Trevor', 'tlarkan@bentley.edu', '7818912219', 'Morison', '230', 'Finance'),"+
					"('Leabman, Jerry', 'jleabman@bentley.edu', '7818912879', 'Morison', '357', 'Finance'),"+
					"('Milton, David', 'dmilton@bentley.edu', '7818912734', 'Adamian', '229', 'Finance'),"+
					"('Minnick, Kristina', 'kminnick@bentley.edu', '7818912941', 'Adamian', '223', 'Finance'),"+
					"('Musumeci, James', 'jmusumeci@bentley.edu', '7818912235', 'Morison', '107', 'Finance'),"+
					"('Nelson, David', 'dnelson@bentley.edu', '7818912059', 'Morison', '121', 'Finance'),"+
					"('Page, Michael', 'mpage@bentley.edu', '7818912864', 'Rauch', '310', 'Finance'),"+
					"('Raman, Kartik', 'kraman@bentley.edu', '7818912781', 'Adamian', '219', 'Finance'),"+
					"('Reis, Ebru', 'ereis@bentley.edu', '7818912078', 'Adamian', '221', 'Finance'),"+
					"('Rosenthal, Leonard', 'lrosenthal@bentley.edu', '7818912516', 'Adamian', '225', 'Finance'),"+
					"('Simon, David', 'dsimon@bentley.edu', '7818912489', 'Adamian', '275', 'Finance'),"+
					"('Siy, Peter', 'psiy@bentley.edu', '7818912570', 'Adamian', '273', 'Finance'),"+
					"('Sultan, Jahangir', 'jsultan@bentley.edu', '7818912518', 'Adamian', '271', 'Finance'),"+
					"('Uhlmann, Phillip', 'puhlmann@bentley.edu', '7818913175', 'Adamian', '277', 'Finance'),"+
					"('Wiggins, Roy', 'rwiggins@bentley.edu', '7818913166', 'Morison', '305A', 'Finance'),"+
					"('Willet, Laura', 'lwillet@bentley.edu', '7818912152', 'Adamian', '224', 'Finance'),"+
					"('Campasano, Vincent', 'vcampasano@bentley.edu', '7818912982', 'Adamian', '209', 'Finance'),"+
					"('Caruso, Frank', 'fcaruso@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Chinca, Alain', 'achinca@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Constantino, Raphael', 'rconstantino@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Daal, Elton', 'edaal@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Dahan, Arie', 'adahan@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Daroff, Herbert', 'hdaroff@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('DeSoye, Caitlin', 'cdesoye@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('DiBello, John', 'jdibelio@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Gibble, Richard', 'rgibble@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Gregory, Patrick', 'pgregory@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Hanover, Alain', 'ahanover@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Harrell, Dana', 'dharrell@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Lanton, Linda', 'llanton@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Leone, Eugene', 'eleone@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Noyes, Richard', 'rnoyes@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Pandit, Anurag', 'apandit@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Pope, David', 'dpope@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Rabinovitz, Stephen', 'srabinovitz@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Read, Karen', 'kread@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Rehman, Asif', 'arehman@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Risman, Maksim ', 'mrisman@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Ritsatos, Titos', 'tritsatos@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Schiavo, Michael', 'mschiavo@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Sevigny, Kathleen', 'ksevigny@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Sgro, Anthony', 'asgro@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Stoller, Linda', 'lstoller@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Tocci, Joseph', 'jtocci@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Wasserman, Steven', 'swasserman@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Willard, Justine', 'jwillard@bentley.edu', '7818912059', 'Adamian', '211', 'Finance'),"+
					"('Aylesworth, Andrew', 'aaylesworth@bentley.edu', '7818913149', 'Morison', '216', 'Marketing'),"+
					"('Berger, Paul', 'pberger@bentley.edu', '7818912746', 'Morison', '351', 'Marketing'),"+
					"('Berthon, Pierre', 'pberthon@bentley.edu', '7818913189', 'Morison', '250', 'Marketing'),"+
					"('Butaney, Gul', 'gbutaney@bentley.edu', '7818912545', 'Morison', '235', 'Marketing'),"+
					"('Campbell, Leland', 'lcampbell@bentley.edu', '7818913146', 'Morison', '240', 'Marketing'),"+
					"('Cross, Ian', 'icross@bentley.edu', '7818913188', 'Morison', '238', 'Marketing'),"+
					"('Dobscha, Susan', 'sdobscha@bentley.edu', '7818912042', 'Morison', '275', 'Marketing'),"+
					"('Eshghi, Abdolreza', 'aeshghi@bentley.edu', '7818912288', 'Morison', '203', 'Marketing'),"+
					"('Foxman, Ellen', 'efoxman@bentley.edu', '7818912796', 'Morison', '277', 'Marketing'),"+
					"('Lowe, Phillip', 'plowe@bentley.edu', '7818913139', 'Morison', '242', 'Marketing'),"+
					"('Moore, Kelvyn', 'kmoore@bentley.edu', '7818912276', 'Morison', '254', 'Marketing'),"+
					"('Nasr, Nada', 'nnasr@bentley.edu', '7818913199', 'Morison', '232', 'Marketing'),"+
					"('Pouliopoulos, James', 'jpouliopoulos@bentley.edu', '7818912006', 'Morison', '239', 'Marketing'),"+
					"('Rowland, Andrew', 'arowland@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Sisodia, Rajendra', 'rsisodia@bentley.edu', '7818913461', 'Morison', '205', 'Marketing'),"+
					"('Xia, Lan', 'lxia@bentley.edu', '7818912468', 'Morison', '234', 'Marketing'),"+
					"('Yeohh, Poh-Lin', 'pyeohh@bentley.edu', '7818912261', 'Morison', '276', 'Marketing'),"+
					"('Benson, Steven', 'sbenson@bentley.edu', '7818913184', 'Morison', '212', 'Marketing'),"+
					"('Bernatchez, Michael', 'mbernatchez@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Catalini, Thomas', 'tcatalini@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Collins, Cathleen', 'ccollins@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Etzenberg, Michael', 'metzenberg@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Elwell, Kristin', 'kelwell@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Giaquinto, John', 'jgiaquinto@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Gladstone, Peter', 'pgladstone@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Hickey, Jonathan', 'jhickey@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Jones, Gordon', 'gjones@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Kaye, Alyson', 'akaye@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Kinch, Frederick', 'fkinch@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Landsman, John', 'jlandsman@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Pehlivan Yalcin, Ekin', 'pehiva_ekin@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Pellant, Roberta', 'rpellant@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Schroll, Jodi', 'jschroll@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Simmel, Leslie', 'lsimmel@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Snow, Mari', 'msnow@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Tesler, Michael', 'mtesler@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Verma, Dharmendra', 'dverma@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Weinberg, Bruce', 'bweinberg@bentley.edu', '7818912079', 'Morison', '236', 'Marketing'),"+
					"('Weisman, David', 'dweisman@bentley.edu', '7818912079', 'Morison', '236', 'Marketing')";
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
