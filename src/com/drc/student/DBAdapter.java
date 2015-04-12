package com.drc.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_ROLLNO = "rollno";
	public static final String KEY_NAME = "name";
	public static final String KEY_COURSE = "course";
	public static final String KEY_SEM = "sem";
	public static final String KEY_SUB1 = "sub1";
	public static final String KEY_SUB2 = "sub2";
	public static final String KEY_SUB3 = "sub3";
	public static final String KEY_SUB4 = "sub4";
	public static final String KEY_TOTAL = "total";
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "student";
	private static final String DATABASE_TABLE = "result";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table result (_id integer primary key autoincrement, "
			+ "rollno integer not null, name text not null, "
			+ "course text not null, "
			+ "sem text not null, "
			+ "sub1 text not null,"
			+ "sub2 text not null,"
			+ "sub3 text not null,"
			+ "sub4 text not null,"
			+ "total text not null);";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS result");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a title into the database---
	public long insertTitle(String rollno, String name, String course,
			String sem, String sub1, String sub2, String sub3, String sub4,
			String total) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ROLLNO, rollno);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_COURSE, course);
		initialValues.put(KEY_SEM, sem);
		initialValues.put(KEY_SUB1, sub1);
		initialValues.put(KEY_SUB2, sub2);
		initialValues.put(KEY_SUB3, sub3);
		initialValues.put(KEY_SUB4, sub4);
		initialValues.put(KEY_TOTAL, total);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a particular title---
	public boolean deleteTitle(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROLLNO + "=" + rowId, null) > 0;
	}

	// ---retrieves all the titles---
	public Cursor getAllTitles() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_ROLLNO,
				KEY_NAME, KEY_COURSE, KEY_SEM, KEY_SUB1, KEY_SUB2, KEY_SUB3,
				KEY_SUB4, KEY_TOTAL }, null, null, null, null, null);
	}

	// ---retrieves a particular title---
	public Cursor getTitle(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_ROLLNO, KEY_NAME, KEY_COURSE, KEY_SEM, KEY_SUB1,
				KEY_SUB2, KEY_SUB3, KEY_SUB4, KEY_TOTAL }, KEY_ROLLNO + "="
				+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---updates a title---
	public boolean updateTitle(long rowId, String rollno, String name,
			String course, String sem, String sub1, String sub2, String sub3,
			String sub4, String total) {
		ContentValues args = new ContentValues();
		args.put(KEY_ROLLNO, rollno);
		args.put(KEY_NAME, name);
		args.put(KEY_COURSE, course);
		args.put(KEY_SEM, sem);
		args.put(KEY_SUB1, sub1);
		args.put(KEY_SUB2, sub2);
		args.put(KEY_SUB3, sub3);
		args.put(KEY_SUB4, sub4);
		args.put(KEY_TOTAL, total);
		return db.update(DATABASE_TABLE, args, KEY_ROLLNO + "=" + rowId, null) > 0;
	}
}
